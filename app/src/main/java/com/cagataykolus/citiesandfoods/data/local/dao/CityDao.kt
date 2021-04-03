package com.cagataykolus.citiesandfoods.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cagataykolus.citiesandfoods.model.City
import kotlinx.coroutines.flow.Flow

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * Data Access Object (DAO) for [com.cagataykolus.citiesandfoods.data.local.CityDatabase]
 */
@Dao
interface CityDao {
    /**
     * Inserts [cities] into the [City.TABLE_CITY] table.
     * Duplicate values are replaced in the table.
     * @param cities
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCities(cities: List<City>)

    /**
     * Deletes all the data from the [City.TABLE_CITY] table.
     */
    @Query("DELETE FROM ${City.TABLE_CITY}")
    suspend fun deleteAllCities()

    /**
     * Fetches the data from the [City.TABLE_CITY] table whose id is [cityName].
     * @param cityName is unique ID of [City]
     * @return [Flow] of [City] from database table.
     */
    @Query("SELECT * FROM ${City.TABLE_CITY} WHERE name = :cityName")
    fun getCityByName(cityName: String): Flow<City>

    /**
     * Fetches all the data from the [City.TABLE_CITY] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${City.TABLE_CITY}")
    fun getAllCities(): Flow<List<City>>
}
