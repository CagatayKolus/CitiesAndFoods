package com.cagataykolus.citiesandfoods.ui.foods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.cagataykolus.citiesandfoods.data.repository.FoodRepository
import com.cagataykolus.citiesandfoods.model.Food
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
 * ViewModel for [FoodsViewModel].
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class FoodsViewModel @Inject constructor(private val foodRepository: FoodRepository) :
    ViewModel() {

    private val _foodsLiveData = MutableLiveData<State<List<Food>>>()

    val foodsLiveData: LiveData<State<List<Food>>> = _foodsLiveData

    fun getFoods() {
        viewModelScope.launch {
            foodRepository.getAllFoods()
                .onStart { _foodsLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _foodsLiveData.value = state }
        }
    }
}