package com.task.weatherapp.data.api

import com.task.weatherapp.data.model.SearchCitiesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesApi {

    @GET("find")
    suspend fun search(
        @Query("q") query: String,
        @Query("units") units: String = "metric"
    ): SearchCitiesResponse

}