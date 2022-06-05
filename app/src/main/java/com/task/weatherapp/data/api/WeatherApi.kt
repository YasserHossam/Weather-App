package com.task.weatherapp.data.api

import com.task.weatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("find")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("units") units: String = "metric"
    ): WeatherResponse

}