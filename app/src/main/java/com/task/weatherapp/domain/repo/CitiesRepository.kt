package com.task.weatherapp.domain.repo

import com.task.weatherapp.domain.model.City

interface CitiesRepository {
    suspend fun searchCities(query: String): List<City>
}