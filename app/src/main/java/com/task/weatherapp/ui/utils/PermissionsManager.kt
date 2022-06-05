package com.task.weatherapp.ui.utils

interface PermissionsManager {
    suspend fun requestPermissions(): Boolean
}