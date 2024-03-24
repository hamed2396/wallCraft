package com.example.wallcraft.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.crazylegend.kotlinextensions.activity.showBottomBar
import com.crazylegend.kotlinextensions.root.logError
import com.crazylegend.kotlinextensions.views.gone
import com.crazylegend.kotlinextensions.views.visible
import com.example.wallcraft.R
import com.example.wallcraft.adapters.PhotoAdapter
import com.example.wallcraft.data.models.home.CategoryModel
import com.example.wallcraft.data.models.home.ResponseCategories
import com.example.wallcraft.databinding.FragmentHomeBinding
import com.example.wallcraft.utils.Constants
import com.example.wallcraft.utils.Constants.HOME_CHIP
import com.example.wallcraft.utils.autoSelectChip
import com.example.wallcraft.utils.base.BaseFragment
import com.example.wallcraft.utils.infiniteSnackBar
import com.example.wallcraft.utils.network.NetworkStatus
import com.example.wallcraft.utils.paging.home.HomeLoadMoreAdapter
import com.example.wallcraft.utils.setupChip
import com.example.wallcraft.utils.statusBarIconColor
import com.example.wallcraft.viewmodel.HomeViewModel
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by activityViewModels<HomeViewModel>()

    @Inject
    lateinit var photoAdapter: PhotoAdapter
    private var isFirstTime =
        false //a flag to prevent calling categoryPhoto when chip is selected for the firstTime
    private var color = ""


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        requireActivity().apply {

            statusBarIconColor(false)

          showBottomBar()
        }
        binding.apply {
            goToSearchFragment()
            loadCategoryData()
            loadCategoryPhotosData()
            chipCategoryClickListeners(chipGroup)
            initRecyclerView()
            loadDataState()
            searchByColor()


        }

    }

    private fun searchByColor() {
        binding.imgColorPicker.setOnClickListener {
            MaterialColorPickerDialog
                .Builder(requireContext())
                .setColors(
                    arrayListOf(
                        getString(R.string.colorBlackCode),
                        getString(R.string.colorWhiteCode),
                        getString(R.string.colorYellowCode),
                        getString(R.string.colorOrangeCode),
                        getString(R.string.colorRedCode),
                        getString(R.string.colorPurpleCode),
                        getString(R.string.colorGreenCode),
                        getString(R.string.colorTealCode),
                        getString(R.string.colorBlueCode),
                        getString(R.string.colorMagentaCode),
                        getString(R.string.colorBrownCode),
                        getString(R.string.colorCyanCode)
                    )
                )

                .setColorShape(ColorShape.SQAURE)
                .setColorListener { _, colorHex ->
                    when (colorHex) {
                        getString(R.string.black) -> {
                            color = getString(R.string.black)
                        }

                        getString(R.string.colorWhiteCode) -> {
                            color = getString(R.string.white)
                        }

                        getString(R.string.colorYellowCode) -> {
                            color = getString(R.string.yellow)
                        }

                        getString(R.string.colorOrangeCode) -> {
                            color = getString(R.string.orange)
                        }

                        getString(R.string.colorRedCode) -> {
                            color = getString(R.string.red)
                        }

                        getString(R.string.colorPurpleCode) -> {
                            color = getString(R.string.purple)
                        }

                        getString(R.string.colorGreenCode) -> {
                            color = getString(R.string.green)
                        }

                        getString(R.string.colorTealCode) -> {
                            color = getString(R.string.teal)
                        }

                        getString(R.string.colorBlueCode) -> {
                            color = getString(R.string.blue)
                        }

                        getString(R.string.colorMagentaCode) -> {
                            color = getString(R.string.magenta)
                        }

                        getString(R.string.colorBrownCode) -> {
                            color = getString(R.string.brown)
                        }

                        getString(R.string.colorCyanCode) -> {
                            color = getString(R.string.cyan)
                        }
                    }

                    HomeFragmentDirections.actionHomeFragmentToSearchFragment(color, isColor = true)
                        .apply {
                            findNavController().navigate(this)
                        }
                }
                .show()
        }
    }

    private fun goToSearchFragment() {
        binding.apply {

            edtSearch.setEndIconOnClickListener {
                if (txtEdt.text?.isNotEmpty() == true) {
                    val extras: FragmentNavigator.Extras =
                        FragmentNavigatorExtras(txtEdt to txtEdt.transitionName)
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToSearchFragment(
                             txtEdt.text.toString() ,txtEdt.transitionName
                        ), extras
                    )
                    enterTransition = MaterialElevationScale(false).apply {
                        duration = 300
                    }
                    reenterTransition = MaterialElevationScale(true).apply {
                        duration = 300

                    }

                }
            }
        }
    }


    private fun loadCategoryPhotosData() {
        binding.apply {
            viewModel.homePhotos.observe(viewLifecycleOwner) {
                photoAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.photosList.apply {
            layoutManager =
                GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
            adapter =
                photoAdapter.withLoadStateFooter(HomeLoadMoreAdapter { photoAdapter.retry() })
            photoAdapter.setOnItemClickListener { id, view ->
                val extras: FragmentNavigator.Extras =
                    FragmentNavigatorExtras(view.imgCover to view.imgCover.transitionName)
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToDetail(
                        id,
                        view.imgCover.transitionName
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
            photoAdapter.addLoadStateListener {
                when (it.source.refresh) {
                    is LoadState.Loading -> {
                        loading.visible()
                        photosList.gone()
                    }

                    is LoadState.NotLoading -> {
                        loading.gone()
                        photosList.visible()
                    }

                    is LoadState.Error -> {}
                }

            }

        }

    }

    private fun loadCategoryData() {
        binding.apply {
            viewModel.categoryItems.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkStatus.Error -> {
                        it.error.logError()
                        root.infiniteSnackBar(
                            getString(R.string.categoryLoadFailed),
                            getString(R.string.retry),
                            action = { viewModel.getCategory() },
                            actionColor = R.color.blue,
                            view = guideline2
                        )
                        veilCategory.apply {
                            gone()
                            unVeil()
                        }
                    }

                    is NetworkStatus.Loading -> {

                        veilCategory.veil()
                    }

                    is NetworkStatus.Success -> {

                        setupCategoryChips(binding.chipGroup, it.success!!)

                        horScrollView.visible()
                        isFirstTime = true
                        autoSelectChips(chipGroup, Constants.WALLPAPERS)
                        veilCategory.apply {
                            gone()
                            unVeil()
                        }

                    }
                }
            }
        }
    }

    private fun setupCategoryChips(chip: ChipGroup, categories: ResponseCategories) {
        val list = mutableListOf<CategoryModel>()
        categories.forEach { category ->
            val categoryModel = CategoryModel()
            categoryModel.id = category.id
            categoryModel.name = category.title
            list.add(categoryModel)
        }

        chip.setupChip(list)
    }

    private fun autoSelectChips(chipGroup: ChipGroup, chipText: String) {

        chipGroup.autoSelectChip(chipText)


    }

    private fun chipCategoryClickListeners(chipGroup: ChipGroup) {
        binding.apply {
            chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                var chip: Chip
                checkedIds.forEach {
                    chip = group.findViewById(it)
                    HOME_CHIP = chip.tag.toString()
                    if (isFirstTime.not()) {
                        viewModel.getHomePhotoId(HOME_CHIP)
                    }
                    isFirstTime = false


                }
            }
        }
    }

}

