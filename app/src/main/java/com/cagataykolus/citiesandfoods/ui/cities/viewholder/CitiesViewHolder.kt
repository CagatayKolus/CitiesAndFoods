package com.cagataykolus.citiesandfoods.ui.cities.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cagataykolus.citiesandfoods.R
import com.cagataykolus.citiesandfoods.databinding.ItemCityBinding
import com.cagataykolus.citiesandfoods.model.City

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 */
class CitiesViewHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(city: City, onItemClicked: (City) -> Unit) {
        binding.textviewItemCityTitle.text = city.name
        binding.textviewItemCityDescription.text = city.description
        binding.imageviewItemCityImage.load(city.image) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(city)
        }
    }
}
