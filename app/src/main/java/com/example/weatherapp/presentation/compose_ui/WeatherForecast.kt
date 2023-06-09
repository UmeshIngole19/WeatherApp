package com.example.weatherapp.presentation.compose_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.presentation.WeatherState

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.list?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(content = {
                items(10) {i ->
                    HourlyWeatherDisplay(
                        weatherData = data[i],
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            })
        }
    }
}