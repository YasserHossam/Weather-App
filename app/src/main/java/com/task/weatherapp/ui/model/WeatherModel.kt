package com.task.weatherapp.ui.model

data class WeatherModel(
    val temperature: Double,
    val feelsLikeTemp: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double,
    val weatherStatus: String
)
