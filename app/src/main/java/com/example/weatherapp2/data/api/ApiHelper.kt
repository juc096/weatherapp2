package com.example.weatherapp2.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getWeatherFromID(id : String) = apiService.getWeatherFromID(id)
}