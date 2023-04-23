package com.example.weatherapp.domain.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun String.toGetTime(): String {
    return try {
        val convertedDate = SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(this);
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        sdf.format(convertedDate)
    } catch (e: Exception) {
       e.printStackTrace()
        "Exception in date parsing"
    }
}
