package com.task.weatherapp.domain.usecase

import com.task.weatherapp.common.scheduler.SchedulerProvider
import com.task.weatherapp.domain.model.LatLng
import com.task.weatherapp.domain.repo.LocationRepository
import kotlinx.coroutines.withContext

class GetCurrentLocationUseCase(
    private val locationsRepository: LocationRepository,
    private val schedulerProvider: SchedulerProvider
) {
    suspend fun invoke(): LatLng {
        return withContext(schedulerProvider.io()) {
            return@withContext locationsRepository.getCurrentLocation()
        }
    }
}