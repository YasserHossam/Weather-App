package com.task.weatherapp.data.repo

import com.task.weatherapp.common.BaseMapper
import com.task.weatherapp.data.api.WeatherApi
import com.task.weatherapp.data.model.WeatherRemote
import com.task.weatherapp.domain.model.Weather
import com.task.weatherapp.domain.repo.WeatherRepository

class DefaultWeatherRepository(
    private val weatherApi: WeatherApi,
    private val mapper: BaseMapper<WeatherRemote, Weather>
) : WeatherRepository {

    override suspend fun getWeather(lat: Double, long: Double): Weather {
        val response = weatherApi.getWeather(lat, long)
        return mapper.map(response.currentWeather)
    }

}