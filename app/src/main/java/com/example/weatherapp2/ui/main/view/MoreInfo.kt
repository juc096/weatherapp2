package com.example.weatherapp2.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.weatherapp2.R
import com.example.weatherapp2.data.model.WeatherInfo
import kotlinx.android.synthetic.main.more_info.*

class MoreInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_info)

        val bundle = intent.extras
        if (bundle != null)
            bundle.getParcelable<WeatherInfo?>("weatherInfo")?.let { displayMoreInfo(it) }

    }
    private fun displayMoreInfo(weatherInfo: WeatherInfo) {
        cityNameTextViewMI.text = "${weatherInfo.name}"
        lowTempTextViewMI.text = "${weatherInfo.main.temp_min}"
        highTempTextViewMI.text = "${weatherInfo.main.temp_max}"
        temperatureTextViewMI.text = "${String.format("%.0f", weatherInfo.main.temp)}°"
        humidityValueTextViewMI.text = "${weatherInfo.main.humidity}%"
        feelsLikeTextViewMI.text = "${weatherInfo.weather.first().description}"

        val iconUrl : String =
            "https://openweathermap.org/img/wn/${weatherInfo.weather.first().icon}.png"
        Glide.with(weatherIconImageViewMI.context)
            .load(iconUrl)
            .into(weatherIconImageViewMI)
        
    }

}