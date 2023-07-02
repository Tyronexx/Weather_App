package com.example.jetweatherforecast.network

import com.example.jetweatherforecast.model.Weather
import com.example.jetweatherforecast.model.WeatherObject
import com.example.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

//          https://api.openweathermap.org/data/2.5/forecast/daily?q=lagos&appid=ed60fcfbd110ee65c7150605ea8aceea&units=imperial

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
//    get weather based on parameters (queries) below
    suspend fun getWeather(
        @Query("q") query: String,                           //whatever city we pass into viewmodel is passed down to here (q is asked for in api link)
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = Constants.API_KEY
    ): Weather
}