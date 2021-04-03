package com.cagataykolus.citiesandfoods.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.cagataykolus.citiesandfoods.R
import com.cagataykolus.citiesandfoods.databinding.FragmentHomeBinding
import com.cagataykolus.citiesandfoods.ui.base.BaseViewBindingFragment
import com.cagataykolus.citiesandfoods.ui.home.adapter.HomeTabPageAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * HomeFragment contains tabs for [TabLayout] and displays [com.cagataykolus.citiesandfoods.ui.foods.FoodsFragment]
 *  and [com.cagataykolus.citiesandfoods.ui.cities.CitiesFragment]
 */
@ExperimentalCoroutinesApi
class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>() {
    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()
    }

    private fun initTabLayout() {
        // Tabs Customization
        binding.tablayoutListTab.setSelectedTabIndicatorColor(Color.WHITE)
        binding.tablayoutListTab.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        binding.tablayoutListTab.tabTextColors =
            ContextCompat.getColorStateList(requireContext(), R.color.white)

        // Set different Text Color for Tabs for when are selected or not
        binding.tablayoutListTab.setTabTextColors(
            (ContextCompat.getColor(
                requireContext(),
                R.color.black80
            )), (ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        )

        // Number Of Tabs
        val numberOfTabs = 2

        // Set Tabs in the center
        //binding.tablayoutListTab.tabGravity = TabLayout.GRAVITY_CENTER

        // Show all Tabs in screen
        binding.tablayoutListTab.tabMode = TabLayout.MODE_FIXED

        // Scroll to see all Tabs
        //binding.tablayoutListTab.tabMode = TabLayout.MODE_SCROLLABLE

        // Set Tab icons next to the text, instead of above the text
        binding.tablayoutListTab.isInlineLabel = true

        // Set the ViewPager Adapter
        val adapter =
            HomeTabPageAdapter(
                requireActivity().supportFragmentManager,
                lifecycle,
                numberOfTabs
            )
        binding.viewpager2ListScreen.adapter = adapter

        // Enable Swipe
        binding.viewpager2ListScreen.isUserInputEnabled = true

        // Link the TabLayout and the ViewPager2 together and Set Text & Icons
        TabLayoutMediator(
            binding.tablayoutListTab,
            binding.viewpager2ListScreen
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.cities)
                }
                1 -> {
                    tab.text = getString(R.string.foods)
                }
            }
            // Change color of the icons
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()


        setCustomTabTitles()
    }

    private fun setCustomTabTitles() {
        val vg = binding.tablayoutListTab.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount

        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup

            val tabChildCount = vgTab.childCount

            for (i in 0 until tabChildCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {

                    // Change Font and Size
                    val font = ResourcesCompat.getFont(requireContext(), R.font.montserrat_bold)
                    tabViewChild.typeface = font
                    tabViewChild.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                }
            }
        }
    }
}