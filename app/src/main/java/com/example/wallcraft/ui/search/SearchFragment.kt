package com.example.wallcraft.ui.search

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.crazylegend.kotlinextensions.fragments.compatColor
import com.crazylegend.kotlinextensions.root.logError
import com.crazylegend.kotlinextensions.views.gone
import com.crazylegend.kotlinextensions.views.textColor
import com.crazylegend.kotlinextensions.views.visible
import com.example.wallcraft.R
import com.example.wallcraft.adapters.SearchAdapter
import com.example.wallcraft.databinding.FragmentSearchBinding
import com.example.wallcraft.utils.base.BaseFragment
import com.example.wallcraft.utils.launchLifeCycleScope
import com.example.wallcraft.utils.paging.home.HomeLoadMoreAdapter
import com.example.wallcraft.viewmodel.SearchViewModel
import com.github.dhaval2404.colorpicker.R.color.blue_400
import com.github.dhaval2404.colorpicker.R.color.brown_400
import com.github.dhaval2404.colorpicker.R.color.green_400
import com.github.dhaval2404.colorpicker.R.color.orange_400
import com.github.dhaval2404.colorpicker.R.color.purple_400
import com.github.dhaval2404.colorpicker.R.color.red_400
import com.github.dhaval2404.colorpicker.R.color.teal_400
import com.github.dhaval2404.colorpicker.R.color.yellow_400
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val args by navArgs<SearchFragmentArgs>()
    private val viewModel by viewModels<SearchViewModel>()

    @Inject
    lateinit var searchAdapter: SearchAdapter

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSearchPhotoId(args.title)
        if (args.transition.isNotEmpty()) {
            MaterialContainerTransform().apply {
                interpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR
                sharedElementEnterTransition = MaterialContainerTransform().apply {
                    drawingViewId = R.id.fragmentContainerView
                    duration = 400
                    scrimColor = Color.TRANSPARENT
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding.apply {

            binding.txtTitle.apply {
                text = args.title
                args.title.logError()
                if (args.transition.isNotEmpty()) transitionName = args.transition
                if (args.isColor) handleTextColor()

            }

            initRecyclerView()
            loadSearchPhotosData()
            loadDataState()


        }

    }

    private fun handleTextColor() {
        binding.txtTitle.textColor = when (args.title) {
            getString(R.string.black) -> compatColor(R.color.lotion)
            getString(R.string.white) -> compatColor(R.color.lotion)
            getString(R.string.yellow) -> compatColor(yellow_400)
            getString(R.string.orange) -> compatColor(orange_400)
            getString(R.string.red) -> compatColor(red_400)
            getString(R.string.purple) -> compatColor(purple_400)
            getString(R.string.magenta) -> compatColor(R.color.magenta)
            getString(R.string.green) -> compatColor(green_400)
            getString(R.string.teal) -> compatColor(teal_400)
            getString(R.string.blue) -> compatColor(blue_400)
            getString(R.string.brown) -> compatColor(brown_400)
            getString(R.string.brown) -> compatColor(brown_400)
            getString(R.string.brown) -> compatColor(R.color.cyan)
            else -> compatColor(R.color.lotion)
        }
    }

    private fun loadSearchPhotosData() {
        binding.apply {
            viewModel.searchPhotos.observe(viewLifecycleOwner) {
                searchAdapter.submitData(lifecycle, it)
                launchLifeCycleScope {
                    delay(1000)
                    imgEmpty.isVisible = searchAdapter.itemCount <= 0
                }


            }
        }
    }

    private fun initRecyclerView() {
        binding.photosList.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter =
                searchAdapter.withLoadStateFooter(HomeLoadMoreAdapter { searchAdapter.retry() })
            searchAdapter.setOnItemClickListener { id, image ->
                val extras: FragmentNavigator.Extras =
                    FragmentNavigatorExtras(image to image.transitionName)
                SearchFragmentDirections.actionSearchToDetail(id, image.transitionName).apply {
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

    private fun loadDataState() {
        binding.apply {
            searchAdapter.addLoadStateListener {
                when (it.source.refresh) {
                    is LoadState.Loading -> {
                        photosList.gone()
                        loading.visible()
                        searchAdapter.itemCount.logError()

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


}