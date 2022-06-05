package com.task.weatherapp.data.mapper

import com.task.weatherapp.common.BaseMapper
import com.task.weatherapp.data.model.CityRemote
import com.task.weatherapp.data.model.WeatherRemote
import com.task.weatherapp.domain.model.City
import com.task.weatherapp.domain.model.Weather

object WeatherRemoteMapper : BaseMapper<WeatherRemote, Weather>() {
    override fun map(input: WeatherRemote): Weather {
        return Weather(
            temperature = input.temperature,
            feelsLikeTemp = input.feelsLikeTemp,
            pressure = input.pressure,
            humidity = input.humidity,
            windSpeed = input.windSpeed,
            weatherStatus = input.weatherState.state
        )
    }
}