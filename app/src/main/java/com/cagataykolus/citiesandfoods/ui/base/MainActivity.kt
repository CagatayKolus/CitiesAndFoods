package com.cagataykolus.citiesandfoods.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import com.cagataykolus.citiesandfoods.R
import com.cagataykolus.citiesandfoods.databinding.ActivityMainBinding

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * [MainActivity] contains fragments.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.main_navigation_fragment)
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            else -> {
                findNavController(R.id.main_navigation_fragment).navigateUp() || super.onSupportNavigateUp()
                return
            }
        }
    }
}