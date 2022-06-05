package com.task.weatherapp.ui.utils

import android.Manifest
import android.content.Context

class LocationPermissionsManager(context: Context) : PermissionsManager(context) {
    override fun permissions(): Array<String> {
        return arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}