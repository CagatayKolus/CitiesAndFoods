package com.cagataykolus.citiesandfoods.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.cagataykolus.citiesandfoods.R
import com.cagataykolus.citiesandfoods.databinding.FragmentDetailBinding
import com.cagataykolus.citiesandfoods.model.City
import com.cagataykolus.citiesandfoods.model.Food
import com.cagataykolus.citiesandfoods.ui.base.BaseViewBindingFragment

/**
 * Created by Çağatay Kölüş on 3.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * DetailFragment contains details of [City] or [Food]
 */
class DetailFragment : BaseViewBindingFragment<FragmentDetailBinding>() {
    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater)

    companion object {
        const val CITY_CONTENT = "CITY"
        const val FOOD_CONTENT = "FOOD"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityContent = arguments?.getParcelable<City>(CITY_CONTENT)
        cityContent?.let {
            showDetailUI(it)
        }

        val foodContent = arguments?.getParcelable<Food>(FOOD_CONTENT)
        foodContent?.let {
            showDetailUI(it)
        }
    }

    private fun showDetailUI(city: City) {
        binding.textviewDetailTitle.text = city.name
        binding.textviewDetailDescription.text = city.description
        binding.imageviewDetailImage.load(city.image) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }
        binding.imageviewDetailContentIcon.load(R.drawable.ic_city)
    }

    private fun showDetailUI(food: Food) {
        binding.textviewDetailTitle.text = food.name
        binding.textviewDetailDescription.visibility = View.GONE
        binding.imageviewDetailImage.load(food.image) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }
        binding.imageviewDetailContentIcon.load(R.drawable.ic_food)
    }
}