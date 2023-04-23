package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.remote.CityDto
import com.example.weatherapp.data.remote.CoordDto
import com.example.weatherapp.data.remote.ForecastDto
import com.example.weatherapp.data.remote.MainDto
import com.example.weatherapp.data.remote.NWeatherDto
import com.example.weatherapp.data.remote.WListDto
import com.example.weatherapp.data.remote.WindDto
import com.example.weatherapp.domain.weather.City
import com.example.weatherapp.domain.weather.Coord
import com.example.weatherapp.domain.weather.Forecast
import com.example.weatherapp.domain.weather.Main
import com.example.weatherapp.domain.weather.WList
import com.example.weatherapp.domain.weather.Weather
import com.example.weatherapp.domain.weather.WeatherType
import com.example.weatherapp.domain.weather.Wind

fun ForecastDto.toForecast(): Forecast {
    return Forecast(
        cod = cod ?: "",
        message = message ?: 0,
        cnt = cnt ?: 0,
        list = list.toWList(),
        city = city?.toCity() ?: City(0, "", coord = Coord(0.0, 0.0), ""),
    )
}


fun CityDto.toCity(): City {
    return City(
        id = id ?: 0,
        name = name ?: "",
        coord = coord?.toCoord() ?: Coord(0.0, 0.0),
        country = country ?: ""
    )
}

fun CoordDto.toCoord(): Coord {
    return Coord(
        lat = lat ?: 0.0,
        lon = lon ?: 0.0
    )
}


fun ArrayList<WListDto>.toWList(): ArrayList<WList> {
    val wList = ArrayList<WList>()
    this.forEach {
        wList.add(
            WList(
                dt = it.dt ?: 0,
                main = it.main?.toMain() ?: Main(0.0, 0, 0, 0, 0.0),
                weather = it.weather.toWeather(),
                wind = it.wind?.toWind() ?: Wind(0.0, 0, 0.0),
                dtTxt = it.dtTxt ?: ""
            )
        )
    }
    return wList
}

fun MainDto.toMain(): Main {
    return Main(
        temp = (temp?.minus(273.15)) ?: 0.0,
        pressure = pressure ?: 0,
        grndLevel = grndLevel ?: 0,
        humidity = humidity ?: 0,
        tempKf = tempKf ?: 0.0
    )
}

fun WindDto.toWind(): Wind {
    return Wind(
        speed = speed ?: 0.0,
        gust = gust ?: 0.0,
        deg = deg ?: 0
    )
}


fun ArrayList<NWeatherDto>.toWeather(): ArrayList<Weather> {
    val weatherList = ArrayList<Weather>()
    this.forEach {
        weatherList.add(
            Weather(
                id = it.id ?: 0,
                main = it.main ?: "",
                description = it.description ?: "",
                icon = WeatherType.fromWMO(it.id!!, it.icon)
            )
        )
    }
    return weatherList
}
