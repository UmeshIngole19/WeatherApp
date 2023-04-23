package com.example.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast/")
    suspend fun getCityWeatherData(
        @Query("q") q: String,
        @Query("appId") appId:String ="e8b35ea61286527cff6d27994f63bd6d"
    ): ForecastDto

    @GET("forecast/")
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appId") appId:String ="e8b35ea61286527cff6d27994f63bd6d"
    ): ForecastDto
}