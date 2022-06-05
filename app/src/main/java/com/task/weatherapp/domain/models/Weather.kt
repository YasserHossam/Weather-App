package com.task.weatherapp.domain.models

data class Weather(
    val temperature: Double,
    val feelsLikeTemp: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double,
    val weatherStatus: String
)
