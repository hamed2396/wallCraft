package com.example.wallcraft.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.crazylegend.kotlinextensions.fragments.add
import com.crazylegend.kotlinextensions.views.gone
import com.crazylegend.kotlinextensions.views.visible
import com.example.wallcraft.R
import com.example.wallcraft.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
     val binding get() = _binding!!
    private val navHost by lazy { supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
      // applyInsets()
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            statusBarColor = Color.TRANSPARENT
            // Change color of status bar icons

        }
        binding.apply {
        bottomNav.apply {
            setupWithNavController(navHost.navController)
            setOnItemReselectedListener {  }
        }
            navHost.navController.addOnDestinationChangedListener { _, destination, _ ->

                when (destination.id) {
                    R.id.splashFragment -> {
                        bottomNav.gone()
                    }

                    R.id.detailFragment -> {
                        bottomNav.gone()
                    }

                    R.id.searchFragment -> {
                        bottomNav.gone()
                    }
                    R.id.detailInfoFragment -> {
                        bottomNav.gone()
                    }



                    else -> {
                        bottomNav.visible()
                    }
                }
            }

        }

    }



    private fun applyInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(
            binding.root
        ) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }





}