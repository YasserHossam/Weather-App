package com.task.weatherapp.domain.repos

import com.task.weatherapp.domain.models.City

interface CitiesRepository {
    suspend fun searchCities(query: String): List<City>
}