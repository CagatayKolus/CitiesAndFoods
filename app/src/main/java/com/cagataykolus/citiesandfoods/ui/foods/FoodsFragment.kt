package com.cagataykolus.citiesandfoods.ui.foods

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
import dagger.hilt.android.AndroidEntryPoint
import com.cagataykolus.citiesandfoods.databinding.FragmentCitiesAndFoodsBinding
import com.cagataykolus.citiesandfoods.model.Food
import com.cagataykolus.citiesandfoods.model.State
import com.cagataykolus.citiesandfoods.ui.base.BaseViewBindingFragment
import com.cagataykolus.citiesandfoods.ui.detail.DetailFragment
import com.cagataykolus.citiesandfoods.ui.foods.adapter.FoodsAdapter
import com.cagataykolus.citiesandfoods.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * FoodsFragment is fragment for [Food] data.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FoodsFragment : BaseViewBindingFragment<FragmentCitiesAndFoodsBinding>() {

    val mViewModel: FoodsViewModel by viewModels()

    private val mAdapter = FoodsAdapter(this::onItemClicked)

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCitiesAndFoodsBinding = FragmentCitiesAndFoodsBinding.inflate(inflater)

    override fun onStart() {
        super.onStart()

        observeFoods()
        handleNetworkChanges()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.run {
            recyclerviewCitiesAndFoodsList.adapter = mAdapter
            swiperefreshlayoutCitiesAndFoodsRefresh.setOnRefreshListener { getFoods() }
        }

        mViewModel.foodsLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getFoods()
            }
        }
    }

    private fun observeFoods() {
        mViewModel.foodsLiveData.observe(viewLifecycleOwner) { state ->
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

    private fun getFoods() = mViewModel.getFoods()

    private fun showLoading(isLoading: Boolean) {
        binding.swiperefreshlayoutCitiesAndFoodsRefresh.isRefreshing = isLoading
    }

    private fun onItemClicked(food: Food) {
        navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundleOf(
                DetailFragment.FOOD_CONTENT to food
            )
        )
    }

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
                if (mViewModel.foodsLiveData.value is State.Error || mAdapter.itemCount == 0) {
                    getFoods()
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