package com.task.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val currentWeather: WeatherRemote
)