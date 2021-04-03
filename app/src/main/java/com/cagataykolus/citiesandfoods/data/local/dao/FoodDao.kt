package com.cagataykolus.citiesandfoods.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cagataykolus.citiesandfoods.model.Food
import kotlinx.coroutines.flow.Flow

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * Data Access Object (DAO) for [com.cagataykolus.citiesandfoods.data.local.FoodDatabase]
 */
@Dao
interface FoodDao {
    /**
     * Inserts [foods] into the [Food.TABLE_FOOD] table.
     * Duplicate values are replaced in the table.
     * @param foods
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFoods(foods: List<Food>)

    /**
     * Deletes all the data from the [Food.TABLE_FOOD] table.
     */
    @Query("DELETE FROM ${Food.TABLE_FOOD}")
    suspend fun deleteAllFoods()

    /**
     * Fetches the data from the [Food.TABLE_FOOD] table whose id is [foodName].
     * @param foodName is unique ID of [Food]
     * @return [Flow] of [Food] from database table.
     */
    @Query("SELECT * FROM ${Food.TABLE_FOOD} WHERE name = :foodName")
    fun getFoodByName(foodName: String): Flow<Food>

    /**
     * Fetches all the data from the [Food.TABLE_FOOD] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Food.TABLE_FOOD}")
    fun getAllFoods(): Flow<List<Food>>
}
