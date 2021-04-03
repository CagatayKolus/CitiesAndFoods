package com.cagataykolus.citiesandfoods.data.repository

import androidx.annotation.MainThread
import com.cagataykolus.citiesandfoods.data.local.dao.CityDao

import com.cagataykolus.citiesandfoods.data.remote.api.NpointService
import com.cagataykolus.citiesandfoods.model.City
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

interface CityRepository {
    fun getAllCities(): Flow<Resource<List<City>>>
    fun getCityByName(cityName: String): Flow<City>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultCityRepository @Inject constructor(
    private val cityDao: CityDao,
    private val service: NpointService
) : CityRepository {
    /**
     * Fetched the data from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllCities(): Flow<Resource<List<City>>> {
        return object : NetworkBoundRepository<List<City>, List<City>>() {

            override suspend fun saveRemoteData(response: List<City>) = cityDao.addCities(response)

            override fun fetchFromLocal(): Flow<List<City>> = cityDao.getAllCities()

            override suspend fun fetchFromRemote(): Response<List<City>> = service.getCities()
        }.asFlow()
    }

    /**
     * Retrieves data with specified [cityName].
     * @param cityName unique id of a [City].
     * @return [City] data fetched from the database.
     */
    @MainThread
    override fun getCityByName(cityName: String): Flow<City> =
        cityDao.getCityByName(cityName).distinctUntilChanged()
}