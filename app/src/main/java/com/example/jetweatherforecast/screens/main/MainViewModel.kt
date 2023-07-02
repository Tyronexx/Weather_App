package com.example.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather
import com.example.jetweatherforecast.model.WeatherObject
import com.example.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
):ViewModel() {

/*
    val data: MutableState<DataOrException<Weather, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Lagos")
    }

//    we can pass in any city we want to search
    private fun getWeather(city: String) {
        viewModelScope.launch {
            if(city.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(cityQuery = city)
//            if data is not empty that means loading has finished (set loading to false)
            if (data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
            Log.d("GET$$","getWeather: ${data.value.data.toString()}")
        }
    }
*/


//    city will be passes when this function is called
    suspend fun getWeatherData(city: String):DataOrException<Weather,Boolean,Exception> {
        return repository.getWeather(cityQuery = city)
    }


}