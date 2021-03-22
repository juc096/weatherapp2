package com.example.weatherapp2.data.api

import com.example.weatherapp2.data.model.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(WEATHER_URL)
    suspend fun getWeatherFromID(@Query("id") id : String,
                                 @Query("appid") key : String = API_KEY,
                                 @Query("units") units : String = UNITS ) : WeatherInfo

    companion object {
        const val API_KEY = "e4b6ccb1e1995dc118fd3cfa5c6ddb62"
        const val WEATHER_URL = "data/2.5/weather"
        const val UNITS = "imperial"
    }

}