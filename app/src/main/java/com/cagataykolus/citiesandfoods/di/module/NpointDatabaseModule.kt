package com.cagataykolus.citiesandfoods.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.cagataykolus.citiesandfoods.data.local.CityDatabase
import com.cagataykolus.citiesandfoods.data.local.FoodDatabase
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

@InstallIn(SingletonComponent::class)
@Module
class NpointDatabaseModule {
    @Singleton
    @Provides
    fun provideFoodDatabase(application: Application) = FoodDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideFoodDao(database: FoodDatabase) = database.getFoodDao()

    @Singleton
    @Provides
    fun provideCityDatabase(application: Application) = CityDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideCityDao(database: CityDatabase) = database.getCityDao()
}