package com.cagataykolus.citiesandfoods.ui.cities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.cagataykolus.citiesandfoods.R
import com.cagataykolus.citiesandfoods.databinding.FragmentCitiesAndFoodsBinding
import com.cagataykolus.citiesandfoods.model.City
import com.cagataykolus.citiesandfoods.model.State
import com.cagataykolus.citiesandfoods.ui.base.BaseViewBindingFragment
import com.cagataykolus.citiesandfoods.ui.cities.adapter.CitiesAdapter
import com.cagataykolus.citiesandfoods.ui.detail.DetailFragment
import com.cagataykolus.citiesandfoods.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * CitiesFragment is fragment for [City] data.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CitiesFragment : BaseViewBindingFragment<FragmentCitiesAndFoodsBinding>() {

    val mViewModel: CitiesViewModel by viewModels()

    private val mAdapter = CitiesAdapter(this::onItemClicked)

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCitiesAndFoodsBinding = FragmentCitiesAndFoodsBinding.inflate(inflater)

    override fun onStart() {
        super.onStart()

        observeCities()
        handleNetworkChanges()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.run {
            recyclerviewCitiesAndFoodsList.adapter = mAdapter
            swiperefreshlayoutCitiesAndFoodsRefresh.setOnRefreshListener { getCities() }
        }

        mViewModel.citiesLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getCities()
            }
        }
    }

    private fun observeCities() {
        mViewModel.citiesLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    showToast(state.message)
                    showLoading(false)
                }
            }
        }
    }

    private fun getCities() = mViewModel.getCities()

    private fun showLoading(isLoading: Boolean) {
        binding.swiperefreshlayoutCitiesAndFoodsRefresh.isRefreshing = isLoading
    }

    private fun onItemClicked(city: City) {
        navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundleOf(
                DetailFragment.CITY_CONTENT to city
            )
        )
    }

    /**
     * Observes network changes.
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.textviewCitiesAndFoodsNetworkStatus.text =
                    getString(R.string.internet_connectivity_fail)
                binding.linearlayoutCitiesAndFoodsNetworkStatus.apply {
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_fail
                        )
                    )
                }
            } else {
                if (mViewModel.citiesLiveData.value is State.Error || mAdapter.itemCount == 0) {
                    getCities()
                }
                binding.textviewCitiesAndFoodsNetworkStatus.text =
                    getString(R.string.internet_connectivity_success)
                binding.linearlayoutCitiesAndFoodsNetworkStatus.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_success
                        )
                    )

                    animate()
                        .alpha(1f)
                        .setStartDelay(1000L)
                        .setDuration(1000L)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }
}