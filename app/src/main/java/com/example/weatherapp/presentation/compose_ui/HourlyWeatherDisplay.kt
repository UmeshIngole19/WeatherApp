package com.example.weatherapp.presentation.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.util.toGetTime
import com.example.weatherapp.domain.weather.WList
import kotlin.math.roundToInt

@Composable
fun HourlyWeatherDisplay(
    weatherData: WList,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    val formattedTime = remember(weatherData) {
        weatherData.dtTxt.toGetTime()
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            color = Color.LightGray
        )
        Image(
            painter = painterResource(id = weatherData.weather[0].icon.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.main.temp.roundToInt()}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}