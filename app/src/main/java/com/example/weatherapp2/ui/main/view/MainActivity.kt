package com.example.weatherapp2.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp2.R
import com.example.weatherapp2.data.api.ApiHelper
import com.example.weatherapp2.data.api.RetrofitBuilder
import com.example.weatherapp2.data.model.WeatherInfo
import com.example.weatherapp2.ui.base.ViewModelFactory
import com.example.weatherapp2.ui.main.adapter.MainAdapter
import com.example.weatherapp2.ui.main.viewmodel.MainViewModel
import com.example.weatherapp2.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers(getCities())
        Toast.makeText(this, "hi", Toast.LENGTH_LONG).show()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf()) {item -> }
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun getCities() : List<String> {
        return listOf("5350734","5128581", "5391811", "4930956", "5101760", "5746545", "5391959",
        "5368361")
    }
    private fun setupObservers(cities : List<String>) {
        for (city in cities) {
            viewModel.getWeatherFromID(city).observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { infos -> retrieveList(infos) }
                        }
                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }

    private fun retrieveList(weatherInfos: WeatherInfo) {
        adapter.apply {
            addUsers(weatherInfos)
            notifyDataSetChanged()
        }
    }

    /*private fun displayMoreInfo(weatherInfo: WeatherInfo) {
        val intent = Intent(this@MainActivity, MoreInfo::class.java)
        intent.putExtra("high", weatherInfo.main.temp_max)
        intent.putExtra("low", weatherInfo.main.temp_min)
        intent.putExtra()
    }*/
}