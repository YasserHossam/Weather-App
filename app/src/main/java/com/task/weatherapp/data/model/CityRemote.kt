package com.task.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class CityRemote(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("coord")
    val coordination: Coordination,

    @SerializedName("sys")
    val system: System
)


data class Coordination(
    @SerializedName("lat")
    val lat: Double,

    @SerializedName("lon")
    val long: Double
)

data class System(
    @SerializedName("country")
    val country: String
)
