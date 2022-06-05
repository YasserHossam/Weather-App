package com.task.weatherapp.domain.repo

import com.task.weatherapp.domain.model.LatLng

interface LocationRepository {
    suspend fun getCurrentLocation(): LatLng
}