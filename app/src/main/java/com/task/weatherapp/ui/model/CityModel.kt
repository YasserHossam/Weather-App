package com.task.weatherapp.ui.model

data class CityModel(
    val id: Long,
    val name: String,
    val lat: Double,
    val long: Double,
    val countryCode: String
)
