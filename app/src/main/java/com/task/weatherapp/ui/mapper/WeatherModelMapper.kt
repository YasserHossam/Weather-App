package com.task.weatherapp.ui.mapper

import com.task.weatherapp.common.BaseMapper
import com.task.weatherapp.domain.model.Weather
import com.task.weatherapp.ui.model.WeatherModel

object WeatherModelMapper : BaseMapper<Weather, WeatherModel>() {
    override fun map(input: Weather): WeatherModel {
        return WeatherModel(
            temperature = input.temperature,
            feelsLikeTemp = input.feelsLikeTemp,
            pressure = input.pressure,
            humidity = input.humidity,
            windSpeed = input.windSpeed,
            weatherStatus = input.weatherStatus
        )
    }

}