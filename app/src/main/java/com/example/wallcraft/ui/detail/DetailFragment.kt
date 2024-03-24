package com.example.wallcraft.ui.detail

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.crazylegend.kotlinextensions.root.logError
import com.crazylegend.kotlinextensions.views.gone
import com.crazylegend.kotlinextensions.views.visible
import com.example.wallcraft.R
import com.example.wallcraft.data.db.entity.FavoriteEntity
import com.example.wallcraft.data.models.detail.ResponseDetail
import com.example.wallcraft.databinding.FragmentDetailBinding
import com.example.wallcraft.utils.Constants
import com.example.wallcraft.utils.Constants.IMAGE_MIME_TYPE
import com.example.wallcraft.utils.Constants.JPG
import com.example.wallcraft.utils.OptionalDialog
import com.example.wallcraft.utils.base.BaseFragment
import com.example.wallcraft.utils.blurHash.BlurHashDecoder
import com.example.wallcraft.utils.changeTint
import com.example.wallcraft.utils.events.Event
import com.example.wallcraft.utils.events.EventBus
import com.example.wallcraft.utils.launchLifeCycleScope
import com.example.wallcraft.utils.loadImageByBlurHash
import com.example.wallcraft.utils.network.NetworkStatus
import com.example.wallcraft.utils.popBackStack
import com.example.wallcraft.viewmodel.DetailViewModel
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.transition.MaterialContainerTransform
import com.innfinity.permissionflow.lib.requestPermissions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModels by viewModels<DetailViewModel>()
    private var isFavorite = false

    @Inject
    lateinit var entity: FavoriteEntity
    private var downloadId: Long = 0
    private lateinit var imageBitmap: Bitmap

    @Inject
    lateinit var wallpaperManager: WallpaperManager

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MaterialContainerTransform().apply {
            interpolator = AnimationUtils.LINEAR_INTERPOLATOR
            sharedElementEnterTransition = MaterialContainerTransform().apply {
                drawingViewId = R.id.fragmentContainerView
                duration = 400
                scrimColor = Color.TRANSPARENT
            }
        }
    }


    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            requireContext().registerReceiver(
                downloadImageCompleted(), IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
                Context.RECEIVER_NOT_EXPORTED
            )
            binding.optionContainer.transitionName = args.transition

            viewModels.getCategory(args.photoId)
            checkIsFavorite()
            addToFavorite()
            loadPhoto()
            imgBack.setOnClickListener { popBackStack() }

            //show Dialog
            launchLifeCycleScope {
                EventBus.subscribe<Event.ShowDialog>(this) {
                    showSetWallpaperDialog()
                }
            }
        }

    }

    private fun goToDetailInfo(data: ResponseDetail) {
        DetailFragmentDirections.actionToInfo(data).apply {
            findNavController().navigate(this)
        }
    }


    private fun addToFavorite() {
        binding.apply {
            imgLike.setOnClickListener {
                isFavorite = if (isFavorite) {
                    entity.tableId = viewModels.findTableId(args.photoId).tableId
                    viewModels.deleteFavorite(entity)
                    false
                } else {
                    viewModels.insertFavorite(entity)
                    true
                }
                checkIsFavorite()
            }


        }
    }


    private fun checkIsFavorite() {

        viewModels.isFavorite(args.photoId).observe(viewLifecycleOwner) {
            isFavorite = it

            if (it) {
                binding.imgLike.changeTint(R.color.red)
            } else {
                binding.imgLike.changeTint(com.github.dhaval2404.colorpicker.R.color.white)
            }
        }
    }


    private fun loadPhoto() {
        binding.apply {
            viewModels.detailPhoto.observe(viewLifecycleOwner) { it ->
                when (it) {
                    is NetworkStatus.Error -> {
                        loading.gone()
                    }

                    is NetworkStatus.Loading -> {
                        loading.visible()

                    }

                    is NetworkStatus.Success -> {

                        loading.gone()
                        createBitmapFromImageView(it.success?.urls?.regular!!)
                        it.success.apply {
                            entity.id = id
                            entity.image = urls!!.regular
                            entity.blurHash = blurHash
                            imgInfo.setOnClickListener { goToDetailInfo(this) }

                        }
                        val blurHash = BlurHashDecoder.blurHashBitmap(
                            coverImg.resources,
                            it.success.blurHash
                        )
                        coverImg.loadImageByBlurHash(it.success.urls.regular, blurHash)
                        //Download
                        imgDownload.setOnClickListener { _ ->
                            getPermission()
                            it.success.urls.smallS3?.let { url ->
                                downloadImage(url, it.success.slug!!)
                            }
                        }

                    }
                }
            }
        }
    }

    private fun getPermission() {
        launchLifeCycleScope {
            requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).collect {}
        }
    }

    private fun downloadImage(imageFile: String, fileName: String) {
        val dm = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri = Uri.parse(imageFile)
        val request = DownloadManager.Request(downloadUri)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle(fileName)
            .setMimeType(IMAGE_MIME_TYPE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                File.separator + fileName + JPG
            )
        downloadId = dm.enqueue(request)
        //Show progress bar
        binding.downloadLoading.apply {
            visible()
            isIndeterminate = true
        }
    }

    private fun downloadImageCompleted(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId) {
                    binding.apply {
                        downloadLoading.gone()
                        imgDownload.setImageResource(R.drawable.check)
                    }
                }
            }
        }
    }

    private fun createBitmapFromImageView(imageUrl: String) {
        lifecycleScope.launch {
            // Create bitmap from image
            val loader = ImageLoader(requireContext())
            val request = ImageRequest.Builder(requireContext())
                .data(imageUrl)
                .allowHardware(false)
                .build()
            val result = (loader.execute(request) as SuccessResult).drawable
            imageBitmap = (result as BitmapDrawable).bitmap
        }
    }

    private fun showSetWallpaperDialog() {
        val dialog = OptionalDialog.Builder(requireContext())
            .setFirstOption(
                getString(R.string.set_home_screen),
                object : OptionalDialog.OptionalDialogClickListener {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun onClick(dialog: OptionalDialog) {
                        dialog.dismiss()
                        wallpaperManager.setBitmap(
                            imageBitmap,
                            null,
                            true,
                            WallpaperManager.FLAG_SYSTEM,
                        )
                    }
                },
            ).setSecondOption(
                getString(R.string.set_lock_screen),
                object : OptionalDialog.OptionalDialogClickListener {
                    override fun onClick(dialog: OptionalDialog) {
                        dialog.dismiss()
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            wallpaperManager.setBitmap(
                                imageBitmap,
                                null,
                                true,
                                WallpaperManager.FLAG_LOCK,
                            )
                        }
                    }
                },
            ).setThirdOption(
                getString(R.string.set_both),
                object : OptionalDialog.OptionalDialogClickListener {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun onClick(dialog: OptionalDialog) {
                        dialog.dismiss()
                        wallpaperManager.setBitmap(
                            imageBitmap,
                            null,
                            false,
                            WallpaperManager.FLAG_SYSTEM,
                        )
                    }
                },
            )
        dialog.show()
    }



}