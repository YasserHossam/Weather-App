package com.task.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class SearchCitiesResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("cod")
    val responseCode: Int,

    @SerializedName("count")
    val count: Int,

    val cities: List<CityRemote>
)
