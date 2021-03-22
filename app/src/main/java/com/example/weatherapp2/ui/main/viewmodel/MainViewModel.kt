package com.example.weatherapp2.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.weatherapp2.data.repository.MainRepository
import com.example.weatherapp2.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {

    fun getWeatherFromID(id : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getWeatherFromID(id)))
        } catch (exception: Exception) {
            //println("error")
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}