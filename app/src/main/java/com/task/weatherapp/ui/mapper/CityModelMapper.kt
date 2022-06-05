package com.task.weatherapp.ui.mapper

import com.task.weatherapp.common.BaseMapper
import com.task.weatherapp.domain.model.City
import com.task.weatherapp.ui.model.CityModel

object CityModelMapper : BaseMapper<City, CityModel>() {

    override fun map(input: City): CityModel {
        return CityModel(
            id = input.id,
            name = input.name,
            lat = input.lat,
            long = input.long,
            countryCode = input.countryCode
        )
    }

}