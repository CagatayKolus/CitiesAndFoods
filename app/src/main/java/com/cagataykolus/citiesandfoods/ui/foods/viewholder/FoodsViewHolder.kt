package com.cagataykolus.citiesandfoods.ui.foods.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cagataykolus.citiesandfoods.R
import com.cagataykolus.citiesandfoods.databinding.ItemFoodBinding
import com.cagataykolus.citiesandfoods.model.Food

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 */
class FoodsViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(food: Food, onItemClicked: (Food) -> Unit) {
        binding.textviewItemFoodTitle.text = food.name
        binding.imageviewItemFoodImage.load(food.image) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(food)
        }
    }
}
