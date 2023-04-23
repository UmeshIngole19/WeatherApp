package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.domain.weather.Forecast

interface WeatherRepository {
    suspend fun getCityWeatherData(cityName:String): Resource<Forecast>
    suspend fun getCurrentWeatherData(lat: Double, long: Double): Resource<Forecast>
}