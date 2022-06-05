package com.task.weatherapp.domain.model

data class City(
    val id: Long,
    val name: String,
    val lat: Double,
    val long: Double,
    val countryCode: String
)