package com.cagataykolus.citiesandfoods.ui.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cagataykolus.citiesandfoods.ui.cities.CitiesFragment
import com.cagataykolus.citiesandfoods.ui.foods.FoodsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * Adapter class for [ViewPager]
 */
@ExperimentalCoroutinesApi
class HomeTabPageAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // # ListPageCitiesFragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "CitiesFragment")
                val page1Fragment = CitiesFragment()
                page1Fragment.arguments = bundle
                return page1Fragment
            }
            1 -> {
                // # ListPageFoodsFragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "FoodsFragment")
                val page2Fragment = FoodsFragment()
                page2Fragment.arguments = bundle
                return page2Fragment
            }
            else -> return CitiesFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}