package com.cagataykolus.citiesandfoods.ui.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.cagataykolus.citiesandfoods.data.repository.CityRepository
import com.cagataykolus.citiesandfoods.model.City
import com.cagataykolus.citiesandfoods.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * ViewModel for [CitiesViewModel].
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class CitiesViewModel @Inject constructor(private val cityRepository: CityRepository) :
    ViewModel() {

    private val _citiesLiveData = MutableLiveData<State<List<City>>>()

    val citiesLiveData: LiveData<State<List<City>>> = _citiesLiveData

    fun getCities() {
        viewModelScope.launch {
            cityRepository.getAllCities()
                .onStart { _citiesLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _citiesLiveData.value = state }
        }
    }
}