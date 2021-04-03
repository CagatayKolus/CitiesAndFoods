package com.cagataykolus.citiesandfoods.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.cagataykolus.citiesandfoods.data.remote.api.NpointService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

@InstallIn(SingletonComponent::class)
@Module
class NpointApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): NpointService = Retrofit.Builder()
        .baseUrl(NpointService.NPOINT_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(NpointService::class.java)
}
