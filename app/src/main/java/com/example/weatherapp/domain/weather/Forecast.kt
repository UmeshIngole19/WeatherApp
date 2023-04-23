package com.example.weatherapp.domain.weather

data class Forecast(
    var cod: String,
    var message: Int,
    var cnt: Int,
    var list: ArrayList<WList> = arrayListOf(),
    var city: City
)

data class WList(
    var dt: Int,
    var main: Main,
    var weather: ArrayList<Weather> = arrayListOf(),
    var wind: Wind,
    var dtTxt: String
)

data class Wind(
    var speed: Double,
    var deg: Int,
    var gust: Double
)

data class Main(
    var temp: Double,
    var pressure: Int,
    var grndLevel: Int,
    var humidity: Int,
    var tempKf: Double
)

data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: WeatherType
)

data class Coord(
    var lat: Double,
    var lon: Double
)

data class City(
    var id: Int,
    var name: String,
    var coord: Coord,
    var country: String,
)
