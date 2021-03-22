package com.example.weatherapp2.data.repository

import com.example.weatherapp2.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getWeatherFromID(id : String) = apiHelper.getWeatherFromID(id)
}