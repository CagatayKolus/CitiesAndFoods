package com.cagataykolus.citiesandfoods.ui.cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cagataykolus.citiesandfoods.databinding.ItemCityBinding
import com.cagataykolus.citiesandfoods.model.City
import com.cagataykolus.citiesandfoods.ui.cities.viewholder.CitiesViewHolder

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [City] along with [CitiesViewHolder]
 * @param onItemClicked which will receive callback when item is clicked.
 */
class CitiesAdapter(
    private val onItemClicked: (City) -> Unit
) : ListAdapter<City, CitiesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CitiesViewHolder(
        ItemCityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean =
                oldItem == newItem
        }
    }
}
