package com.cagataykolus.citiesandfoods.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import com.cagataykolus.citiesandfoods.data.repository.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class CityRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindCityRepository(repository: DefaultCityRepository): CityRepository
}