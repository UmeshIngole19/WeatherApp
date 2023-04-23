package com.example.weatherapp.data.remote

import com.google.gson.annotations.SerializedName


data class ForecastDto(
    @SerializedName("cod")
    var cod: String? = null,
    @SerializedName("message")
    var message: Int? = null,
    @SerializedName("cnt")
    var cnt: Int? = null,
    @SerializedName("list")
    var list: ArrayList<WListDto> = arrayListOf(),
    @SerializedName("city")
    var city: CityDto? = CityDto()
)

data class WListDto(
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("main") var main: MainDto? = MainDto(),
    @SerializedName("weather") var weather: ArrayList<NWeatherDto> = arrayListOf(),
    @SerializedName("wind") var wind: WindDto? = WindDto(),
    @SerializedName("dt_txt") var dtTxt: String? = null
)

data class MainDto(
    @SerializedName("temp") var temp: Double? = null,
    @SerializedName("pressure") var pressure: Int? = null,
    @SerializedName("grnd_level") var grndLevel: Int? = null,
    @SerializedName("humidity") var humidity: Int? = null,
    @SerializedName("temp_kf") var tempKf: Double? = null
)

data class NWeatherDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null
)


data class WindDto(
    @SerializedName("speed") var speed: Double? = null,
    @SerializedName("deg") var deg: Int? = null,
    @SerializedName("gust") var gust: Double? = null
)

data class CoordDto(
    @SerializedName("lat") var lat: Double? = null,
    @SerializedName("lon") var lon: Double? = null
)


data class CityDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("coord") var coord: CoordDto? = CoordDto(),
    @SerializedName("country") var country: String? = null,
    @SerializedName("population") var population: Int? = null,
    @SerializedName("timezone") var timezone: Int? = null,
    @SerializedName("sunrise") var sunrise: Int? = null,
    @SerializedName("sunset") var sunset: Int? = null
)

