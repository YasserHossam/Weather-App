package com.task.weatherapp.ui.viewmodel

import com.task.weatherapp.ui.model.CityModel

sealed class WeatherIntent {
    data class SearchCities(val query: String) : WeatherIntent()

    data class GetWeather(val city: CityModel) : WeatherIntent()

    object GetCurrentLocationWeather : WeatherIntent()
}
