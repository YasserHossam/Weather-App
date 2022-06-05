package com.task.weatherapp.data.repo

import com.task.weatherapp.common.BaseMapper
import com.task.weatherapp.data.api.CitiesApi
import com.task.weatherapp.data.model.CityRemote
import com.task.weatherapp.domain.model.City
import com.task.weatherapp.domain.repo.CitiesRepository

class DefaultCitiesRepository(
    private val citiesApi: CitiesApi,
    private val mapper: BaseMapper<CityRemote, City>
) : CitiesRepository {

    override suspend fun searchCities(query: String): List<City> {
        val response = citiesApi.search(query)
        return mapper.map(response.cities)
    }

}