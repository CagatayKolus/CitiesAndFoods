package com.cagataykolus.citiesandfoods.ui.foods.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cagataykolus.citiesandfoods.databinding.ItemFoodBinding
import com.cagataykolus.citiesandfoods.model.Food
import com.cagataykolus.citiesandfoods.ui.foods.viewholder.FoodsViewHolder

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Food] along with [FoodsViewHolder]
 * @param onItemClicked which will receive callback when item is clicked.
 */
class FoodsAdapter(
    private val onItemClicked: (Food) -> Unit
) : ListAdapter<Food, FoodsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodsViewHolder(
        ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem == newItem
        }
    }
}
