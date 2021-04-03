package com.cagataykolus.citiesandfoods.data.remote.api

import com.cagataykolus.citiesandfoods.model.City
import com.cagataykolus.citiesandfoods.model.Food
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * Service to fetch data using endpoint [NPOINT_API_URL].
 */
interface NpointService {
    @GET("foods")
    suspend fun getFoods(): Response<List<Food>>

    @GET("cities")
    suspend fun getCities(): Response<List<City>>

    companion object {
        const val NPOINT_API_URL = "https://api.npoint.io/a2b63ef226c08553b2f9/"
    }
}
