package com.task.weatherapp.domain.model

data class Weather(
    val temperature: Double,
    val feelsLikeTemp: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double,
    val weatherStatus: String,
    val clouds: Int
)
