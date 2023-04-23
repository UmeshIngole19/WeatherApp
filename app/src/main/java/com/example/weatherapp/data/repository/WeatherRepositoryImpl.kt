package com.example.weatherapp.data.repository

import com.example.weatherapp.data.mappers.toForecast
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.domain.weather.Forecast
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getCityWeatherData(
        cityName: String
    ): Resource<Forecast> {
        return try {
            Resource.Success(
                data = api.getCityWeatherData(
                    q = cityName
                ).toForecast()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getCurrentWeatherData(lat: Double, long: Double): Resource<Forecast> {
        return try {
            Resource.Success(
                data = api.getCurrentWeatherData(
                    lat = lat,
                    lon = long
                ).toForecast()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }


}