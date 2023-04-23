package com.example.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.persistant_data.readString
import com.example.weatherapp.persistant_data.writeString
import com.example.weatherapp.presentation.compose_ui.CityList
import com.example.weatherapp.presentation.compose_ui.SearchView
import com.example.weatherapp.presentation.compose_ui.WeatherCard
import com.example.weatherapp.presentation.compose_ui.WeatherForecast
import com.example.weatherapp.presentation.ui.theme.DarkBlue
import com.example.weatherapp.presentation.ui.theme.DeepBlue
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    var cityName =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            CoroutineScope(Main).launch {
                getSharedData()
            }
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))

        setContent {
            val textState = remember { mutableStateOf(TextFieldValue(cityName)) }
            WeatherAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(8.dp))
                            SearchView(textState)
                            CityList(state = textState){
                                viewModel.getCityWeather(it)
                                CoroutineScope(Main).launch {
                                    applicationContext.writeString(CITY_NAME_KEY,it)
                                }
                            }
                        }
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = DeepBlue
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = viewModel.state)
                    }
                    if(viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }

    private suspend fun getSharedData() {
        CoroutineScope(Main).launch{
            cityName = applicationContext.readString(CITY_NAME_KEY).first()
        }.join()
        if(cityName.isNotEmpty() || cityName != ""){
            viewModel.getCityWeather(cityName)
        }else{
            cityName = ""
            viewModel.loadWeatherInfo()
        }
        Log.d("Befor...:","Yesss")
    }

    companion object{
        val CITY_NAME_KEY = "CityName"
    }
}