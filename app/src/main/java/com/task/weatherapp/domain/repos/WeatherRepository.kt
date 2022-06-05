package com.task.weatherapp.domain.repos

import com.task.weatherapp.domain.models.Weather

interface WeatherRepository {
    suspend fun getWeather(lat: Double, long: Double): Weather
}