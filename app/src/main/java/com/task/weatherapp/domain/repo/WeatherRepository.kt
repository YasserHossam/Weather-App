package com.task.weatherapp.domain.repo

import com.task.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(lat: Double, long: Double): Weather
}