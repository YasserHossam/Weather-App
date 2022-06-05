package com.task.weatherapp.data.mapper

import com.task.weatherapp.common.BaseMapper
import com.task.weatherapp.data.model.CityRemote
import com.task.weatherapp.domain.model.City

object CityRemoteMapper : BaseMapper<CityRemote, City>() {
    override fun map(input: CityRemote): City {
        return City(
            id = input.id,
            name = input.name,
            lat = input.coordination.lat,
            long = input.coordination.long,
            countryCode = input.system.country
        )
    }
}