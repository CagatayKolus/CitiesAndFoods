package com.cagataykolus.citiesandfoods.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cagataykolus.citiesandfoods.model.Food.Companion.TABLE_FOOD
import kotlinx.android.parcel.Parcelize

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

@Parcelize
@Entity(tableName = TABLE_FOOD)
data class Food(
    @PrimaryKey
    @NonNull
    var name: String = "",
    var image: String? = null
) : Parcelable {
    companion object {
        const val TABLE_FOOD = "table_food"
    }
}