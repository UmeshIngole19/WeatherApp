package com.example.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
) {
    object Thunderstorm : WeatherType(
        weatherDesc = "Thunderstorm",
        iconRes = R.drawable.ic_thunderstorm,
    )
    object Drizzle : WeatherType(
        weatherDesc = "Drizzle",
        iconRes = R.drawable.ic_shower_rain
    )
    object Rain : WeatherType(
        weatherDesc = "Rain",
        iconRes = R.drawable.ic_rain
    )

    object RainyNight : WeatherType(
        weatherDesc = "RainyNight",
        iconRes = R.drawable.ic_shower_rain
    )
    object Snow : WeatherType(
        weatherDesc = "Snow",
        iconRes = R.drawable.ic_snow
    )

    object Atmosphere : WeatherType(
        weatherDesc = "Atmosphere",
        iconRes = R.drawable.ic_mist
    )
    object ClearSky : WeatherType(
        weatherDesc = "ClearSky",
        iconRes = R.drawable.ic_clear_sky
    )
    object ClearSkyNight : WeatherType(
        weatherDesc = "ClearSkyNight",
        iconRes = R.drawable.ic_clear_sky_night
    )

    object BrokenClouds : WeatherType(
        weatherDesc = "BrokenClouds",
        iconRes = R.drawable.ic_broken_clouds
    )
    object Clouds : WeatherType(
        weatherDesc = "Clouds",
        iconRes = R.drawable.ic_scattered_clouds
    )
    object CloudsDay : WeatherType(
        weatherDesc = "CloudsDay",
        iconRes = R.drawable.ic_few_clouds
    )
    object CloudsNight : WeatherType(
        weatherDesc = "Clouds",
        iconRes = R.drawable.ic_few_clouds_night
    )

    companion object {
        fun fromWMO(code: Int,icon:String?=null): WeatherType {
            return when(code) {
                in 200..232-> Thunderstorm
                in 300..321-> Drizzle
                in 500..504-> Rain
                in 520..531-> RainyNight
                in 600..622-> Snow
                in 701..781-> Atmosphere
                800 -> {
                    if(icon.equals("01d")){
                        return ClearSky
                    }else{
                        return ClearSkyNight
                    }
                }
                801 -> {
                    if(icon.equals("02d")){
                        return CloudsDay
                    }else{
                        return CloudsNight
                    }
                }
                802 -> Clouds
                803,804-> BrokenClouds

                else -> ClearSky
            }
        }
    }
}