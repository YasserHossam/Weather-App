package com.task.weatherapp.ui.viewmodel

import com.task.weatherapp.ui.model.CityModel
import com.task.weatherapp.ui.model.WeatherModel

sealed class WeatherViewState {
    object Idle : WeatherViewState()

    object Loading : WeatherViewState()

    data class CitiesData(val cities: List<CityModel>) : WeatherViewState()
    data class WeatherData(val weather: WeatherModel) : WeatherViewState()

    sealed class Error : WeatherViewState() {
        object Permission : Error()
        object Network : Error()
        object Server : Error()
    }
}