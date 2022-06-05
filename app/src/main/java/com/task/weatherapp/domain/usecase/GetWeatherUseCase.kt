package com.task.weatherapp.domain.usecase

import com.task.weatherapp.common.scheduler.SchedulerProvider
import com.task.weatherapp.domain.model.Weather
import com.task.weatherapp.domain.repo.WeatherRepository
import kotlinx.coroutines.withContext

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val schedulerProvider: SchedulerProvider
) {

    suspend fun invoke(lat: Double, long: Double): Weather {
        return withContext(schedulerProvider.io()) {
            weatherRepository.getWeather(lat, long)
        }
    }
}