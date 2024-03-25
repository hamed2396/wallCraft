package com.example.wallcraft.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.crazylegend.kotlinextensions.root.logError
import com.example.wallcraft.adapters.FavoriteAdapter
import com.example.wallcraft.databinding.FragmentFavoriteBinding
import com.example.wallcraft.utils.Constants
import com.example.wallcraft.utils.SliderTransformer
import com.example.wallcraft.utils.base.BaseFragment
import com.example.wallcraft.utils.blurHash.BlurHashDecoder
import com.example.wallcraft.utils.launchLifeCycleScope
import com.example.wallcraft.utils.loadBlurImage
import com.example.wallcraft.viewmodel.FavoriteViewModel
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val viewModel by viewModels<FavoriteViewModel>()

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter
    private var backFromDetail = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding.apply {
            viewModel.getAllMeals()
            loadAllFavorite()


        }

    }

    private fun loadAllFavorite() {
        viewModel.getAllFavorite.observe(viewLifecycleOwner) {
            binding.imgEmpty.isVisible = it.isEmpty()
            binding.viewpager.apply {


                favoriteAdapter.setData(it)
                adapter = favoriteAdapter
                offscreenPageLimit = 3
                setPageTransformer(SliderTransformer(3))

            }

            binding.viewpager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    backFromDetail.logError()
                    if (backFromDetail.not()) {
                        Constants.PAGER_POSITION = position
                        backFromDetail = false
                    }

                    val blurHash = BlurHashDecoder.blurHashBitmap(resources, it[position].blurHash)
                    binding.bgImage.loadBlurImage(blurHash)


                }

            }

            )




            favoriteAdapter.setOnItemClickListener { id, image ->
                backFromDetail = true
                val extras: FragmentNavigator.Extras =
                    FragmentNavigatorExtras(image to image.transitionName)
                FavoriteFragmentDirections.actionFavToDetail(id, image.transitionName).apply {
                    findNavController().navigate(this, extras)
                }

                enterTransition = MaterialElevationScale(false).apply {
                    duration = 300
                }
                reenterTransition = MaterialElevationScale(true).apply {
                    duration = 300

                }
            }
        }


    }

    override fun onStop() {
        super.onStop()
        backFromDetail = true
    }

    override fun onResume() {
        super.onResume()
        launchLifeCycleScope {
            delay(100)
            backFromDetail = false
        }
        binding.viewpager.currentItem = Constants.PAGER_POSITION
    }


}