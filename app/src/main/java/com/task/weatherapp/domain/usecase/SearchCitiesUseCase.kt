package com.task.weatherapp.domain.usecase

import com.task.weatherapp.common.scheduler.SchedulerProvider
import com.task.weatherapp.domain.models.City
import com.task.weatherapp.domain.repos.CitiesRepository
import kotlinx.coroutines.withContext

class SearchCitiesUseCase(
    private val citiesRepository: CitiesRepository,
    private val schedulerProvider: SchedulerProvider
) {

    suspend fun invoke(query: String): List<City> {
        return withContext(schedulerProvider.io()) {
            citiesRepository.searchCities(query)
        }
    }
}