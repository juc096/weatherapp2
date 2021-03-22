package com.example.weatherapp2.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp2.R
import com.example.weatherapp2.data.model.WeatherInfo
import com.example.weatherapp2.ui.main.view.MoreInfo


import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val weatherInfos: ArrayList<WeatherInfo>,
                  private val listener: (WeatherInfo) -> Unit) :
                                    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weatherInfo: WeatherInfo) {
            itemView.apply {
                textViewCityName.text = "${weatherInfo.name}"
                textViewTemp.text = "${String.format("%.0f", weatherInfo.main.temp)}°"
                textViewWeatherText.text = "${weatherText(weatherInfo)}"
                Glide.with(imageViewAvatar.context)
                    .load(weatherIcon(weatherInfo))
                    .into(imageViewAvatar)
            }
        }

        private fun weatherText(weatherInfo: WeatherInfo) : String {
            return weatherInfo.weather.first().description
        }
        private fun weatherIcon(weatherInfo: WeatherInfo) : String {
            val iconId : String = weatherInfo.weather.first().icon
            return "https://openweathermap.org/img/wn/${iconId}@2x.png"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = weatherInfos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(weatherInfos[position])
        holder.itemView.setOnClickListener {listener(weatherInfos[position])}
    }

    fun addUsers(weatherInfo: WeatherInfo) {
        this.weatherInfos.apply {
            add(weatherInfo)
        }
    }



}