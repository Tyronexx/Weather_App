package com.example.jetweatherforecast.repository

import android.util.Log
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather
import com.example.jetweatherforecast.model.WeatherObject
import com.example.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
){

    suspend fun getWeather(cityQuery: String)
    :DataOrException<Weather, Boolean, Exception>  {
        val response = try {
            api.getWeather(query = cityQuery)
        }catch (e: Exception){
            Log.d("REX444", "getWeather: $e")
            return DataOrException(e = e)
        }

        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }

}