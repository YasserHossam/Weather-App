package com.task.weatherapp.data.repo

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Tasks
import com.task.weatherapp.domain.model.LatLng
import com.task.weatherapp.domain.repo.LocationRepository

class DefaultLocationRepository(context: Context) : LocationRepository {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): LatLng {
        val lastLocation = Tasks.await(fusedLocationClient.lastLocation)
        return LatLng(lastLocation.latitude, lastLocation.longitude)
    }
}