package com.task.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherRemote(
    @SerializedName("temp")
    val temperature: Double,

    @SerializedName("feels_like")
    val feelsLikeTemp: Double,

    @SerializedName("pressure")
    val pressure: Double,

    @SerializedName("humidity")
    val humidity: Double,

    @SerializedName("wind_speed")
    val windSpeed: Double,

    @SerializedName("weather")
    val weatherState: WeatherState
)

data class WeatherState(
    @SerializedName("main")
    val state: String
)