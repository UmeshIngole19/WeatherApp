package com.example.weatherapp.presentation.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import java.util.Locale


@Composable
fun CityListItem(countryText: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(countryText) })
            .background(colorResource(id = R.color.deepBlue))
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = countryText, fontSize = 18.sp, color = Color.White)
    }
}


@Composable
fun CityList(state: MutableState<TextFieldValue>,selectedCityCallback:(String)->Unit) {
    val countries = getListOfUsCities()
    var filteredCountries: ArrayList<String>
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        filteredCountries = if (searchedText.isEmpty()) {
            ArrayList<String>()
        } else {
            val resultList = ArrayList<String>()
            for (country in countries) {
                if (country.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(country)
                }
            }
            resultList
        }
        items(filteredCountries) { filteredCountry ->
            CityListItem(
                countryText = filteredCountry,
                onItemClick = { selectedCountry ->
                    selectedCityCallback(selectedCountry)
                    state.value = TextFieldValue("")
                }
            )
        }
    }
}

fun getListOfUsCities(): ArrayList<String> {
    val listOfUsCities = ArrayList<String>()
    listOfUsCities.add("New York")
    listOfUsCities.add("California")
    listOfUsCities.add("Illinois")
    listOfUsCities.add("Ohio")
    listOfUsCities.add("Arizona")
    listOfUsCities.add("Pennsylvania")
    listOfUsCities.add("Texas")
    listOfUsCities.add("Washington")
    listOfUsCities.add("Florida")
    listOfUsCities.add("Tennessee")
    listOfUsCities.add("Nevada")
    listOfUsCities.add("Oregon")
    return listOfUsCities
}