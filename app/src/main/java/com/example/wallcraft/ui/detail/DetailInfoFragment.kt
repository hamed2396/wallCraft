package com.example.wallcraft.ui.detail

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.navArgs
import com.crazylegend.kotlinextensions.views.gone
import com.crazylegend.kotlinextensions.views.visible
import com.example.wallcraft.R
import com.example.wallcraft.databinding.FragmentDetailInfoBinding
import com.example.wallcraft.utils.base.BaseBottomSheetFragment
import com.example.wallcraft.utils.commaSeparator
import com.example.wallcraft.utils.events.Event
import com.example.wallcraft.utils.events.EventBus
import com.example.wallcraft.utils.launchLifeCycleScope
import com.example.wallcraft.utils.setupTagsChip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailInfoFragment :
    BaseBottomSheetFragment<FragmentDetailInfoBinding>(FragmentDetailInfoBinding::inflate) {
    private val args by navArgs<DetailInfoFragmentArgs>()
    override fun getTheme(): Int {
        return R.style.RemoveDialogBackground
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireParentFragment().requireView().findViewById<ConstraintLayout>(R.id.optionContainer)
            .gone()
        binding.apply {
            args.info.also {
                txtTitle.text = it.altDescription!!
                txtViews.text = it.views!!.commaSeparator
                it.tagsPreview?.let {
                    chipGroup.setupTagsChip(it)
                }
                it.tags?.get(0)?.title?.let { title -> txtFeatured.text = title }

                txtDownloads.text = it.downloads!!.commaSeparator
            }
            btnSetWallpaper.setOnClickListener {
                launchLifeCycleScope {
                    EventBus.publish(Event.ShowDialog)
                    dismiss()
                }
            }
        }


    }

    override fun onDestroyView() {
        requireParentFragment().requireView().findViewById<ConstraintLayout>(R.id.optionContainer)
            .visible()
        super.onDestroyView()
    }

}