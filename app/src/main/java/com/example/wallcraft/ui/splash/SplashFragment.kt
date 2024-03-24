package com.example.wallcraft.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.crazylegend.kotlinextensions.activity.hideBottomBar
import com.example.wallcraft.R
import com.example.wallcraft.databinding.FragmentSplashBinding
import com.example.wallcraft.utils.base.BaseFragment
import com.example.wallcraft.utils.launchLifeCycleScope
import com.example.wallcraft.utils.statusBarIconColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            requireActivity().apply {
                hideBottomBar()
                statusBarIconColor(false)
            }
            launchLifeCycleScope {
                delay(3000)
                SplashFragmentDirections.actionSplashTo().apply {
                    findNavController().popBackStack(R.id.splashFragment,true)
                    findNavController().navigate(this)

                }
            }

        }

    }


}