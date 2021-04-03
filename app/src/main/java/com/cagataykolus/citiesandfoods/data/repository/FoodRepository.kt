package com.cagataykolus.citiesandfoods.data.repository

import androidx.annotation.MainThread
import com.cagataykolus.citiesandfoods.data.local.dao.FoodDao
import com.cagataykolus.citiesandfoods.data.remote.api.NpointService
import com.cagataykolus.citiesandfoods.model.Food
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

interface FoodRepository {
    fun getAllFoods(): Flow<Resource<List<Food>>>
    fun getFoodByName(foodName: String): Flow<Food>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultFoodRepository @Inject constructor(
    private val foodDao: FoodDao,
    private val service: NpointService
) : FoodRepository {
    /**
     * Fetched the data from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllFoods(): Flow<Resource<List<Food>>> {
        return object : NetworkBoundRepository<List<Food>, List<Food>>() {

            override suspend fun saveRemoteData(response: List<Food>) = foodDao.addFoods(response)

            override fun fetchFromLocal(): Flow<List<Food>> = foodDao.getAllFoods()

            override suspend fun fetchFromRemote(): Response<List<Food>> = service.getFoods()
        }.asFlow()
    }

    /**
     * Retrieves data with specified [foodName].
     * @param foodName unique id of a [Food].
     * @return [Food] data fetched from the database.
     */
    @MainThread
    override fun getFoodByName(foodName: String): Flow<Food> =
        foodDao.getFoodByName(foodName).distinctUntilChanged()
}