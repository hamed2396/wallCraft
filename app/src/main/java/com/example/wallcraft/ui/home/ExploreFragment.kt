package com.example.wallcraft.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.crazylegend.kotlinextensions.views.gone
import com.crazylegend.kotlinextensions.views.visible
import com.example.wallcraft.R
import com.example.wallcraft.adapters.PhotosByOrderAdapter
import com.example.wallcraft.databinding.FragmentExploreBinding
import com.example.wallcraft.utils.Constants
import com.example.wallcraft.utils.base.BaseFragment
import com.example.wallcraft.utils.paging.home.HomeLoadMoreAdapter
import com.example.wallcraft.viewmodel.ExploreViewModel
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate) {


    private val viewModel by viewModels<ExploreViewModel>()

    @Inject
    lateinit var photosByOrderAdapter: PhotosByOrderAdapter

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Constants.TITLE_ORDER=Constants.LATEST
        viewModel.getPhotosByOrder(Constants.LATEST)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding.apply {
            txtTitle.text=Constants.TITLE_ORDER
            loadSearchPhotosData()
            initRecyclerView()
            loadDataState()
            imgFilter.setOnClickListener {
                showPopupMenu()
            }


        }

    }

    private fun loadSearchPhotosData() {
        binding.apply {
            viewModel.photosByOrder.observe(viewLifecycleOwner) {
                photosByOrderAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.photosList.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter =
                photosByOrderAdapter.withLoadStateFooter(HomeLoadMoreAdapter { photosByOrderAdapter.retry() })
            photosByOrderAdapter.setOnItemClickListener { id, image ->
                val extras: FragmentNavigator.Extras =
                    FragmentNavigatorExtras(image.imgCover to image.imgCover.transitionName)
                findNavController().navigate(
                    ExploreFragmentDirections.actionExploreToDetail(
                        id,
                        image.imgCover.transitionName
                    ), extras
                )
                enterTransition = MaterialElevationScale(false).apply {
                    duration = 500
                }
                reenterTransition = MaterialElevationScale(true).apply {
                    duration = 500
                }
            }
        }
    }

    private fun loadDataState() {
        binding.apply {
            photosByOrderAdapter.addLoadStateListener {
                when (it.source.refresh) {
                    is LoadState.Loading -> {
                        photosList.gone()
                        loading.visible()

                    }

                    is LoadState.NotLoading -> {
                        photosList.visible()
                        loading.gone()
                    }

                    is LoadState.Error -> {}
                }

            }

        }

    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(requireContext(), binding.imgFilter, Gravity.END)
        popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.latest -> {
                    viewModel.getPhotosByOrder(Constants.LATEST)
                   Constants.TITLE_ORDER=getString(R.string.Latest)
                    binding.txtTitle.text=Constants.TITLE_ORDER
                    true
                }

                R.id.oldest -> {
                    viewModel.getPhotosByOrder(Constants.OLDEST)
                    Constants.TITLE_ORDER=getString(R.string.oldest)
                    binding.txtTitle.text=Constants.TITLE_ORDER
                    true
                }

                R.id.popular -> {
                    viewModel.getPhotosByOrder(Constants.POPULAR)
                    Constants.TITLE_ORDER=getString(R.string.popular)
                    binding.txtTitle.text=Constants.TITLE_ORDER
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Constants.TITLE_ORDER=Constants.LATEST
    }
}
