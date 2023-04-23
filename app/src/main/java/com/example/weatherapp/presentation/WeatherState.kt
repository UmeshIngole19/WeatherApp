package com.example.weatherapp.presentation

import com.example.weatherapp.domain.weather.Forecast

data class WeatherState(
    val weatherInfo: Forecast? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
