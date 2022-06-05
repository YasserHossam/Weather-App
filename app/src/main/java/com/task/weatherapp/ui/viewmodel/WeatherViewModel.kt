package com.task.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.weatherapp.domain.usecase.GetCurrentLocationUseCase
import com.task.weatherapp.domain.usecase.GetWeatherUseCase
import com.task.weatherapp.domain.usecase.SearchCitiesUseCase
import com.task.weatherapp.ui.mapper.CityModelMapper
import com.task.weatherapp.ui.mapper.WeatherModelMapper
import com.task.weatherapp.ui.model.CityModel
import com.task.weatherapp.ui.utils.PermissionsManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel(
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val citiesMapper: CityModelMapper,
    private val weatherMapper: WeatherModelMapper,
    private val locationPermissionsManager: PermissionsManager
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherViewState>(WeatherViewState.Idle)
    val state: StateFlow<WeatherViewState>
        get() = _state

    private var getCitiesJob: Job? = null

    fun processIntent(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.SearchCities -> processSearchCities(intent.query)
            is WeatherIntent.GetWeather -> processGetWeather(intent.city)
            WeatherIntent.GetCurrentLocationWeather -> processGetCurrentLocationWeather()
        }
    }

    private fun processSearchCities(query: String) {
        getCitiesJob?.cancel()
        if (query.length < 3)
            return
        _state.value = WeatherViewState.Loading
        getCitiesJob = viewModelScope.launch {
            val resultState = try {
                val cities = searchCitiesUseCase.invoke(query)
                    .map(citiesMapper::map)
                WeatherViewState.CitiesData(cities)
            } catch (throwable: Throwable) {
                if (throwable is IOException)
                    WeatherViewState.Error.Network
                else
                    WeatherViewState.Error.Server
            }
            _state.value = resultState
        }
    }

    private fun processGetWeather(city: CityModel) {
        _state.value = WeatherViewState.Loading
        viewModelScope.launch {
            val resultState = try {
                val domainWeather = getWeatherUseCase.invoke(city.lat, city.long)
                val weatherModel = weatherMapper.map(domainWeather)
                WeatherViewState.WeatherData(weatherModel)
            } catch (throwable: Throwable) {
                if (throwable is IOException)
                    WeatherViewState.Error.Network
                else
                    WeatherViewState.Error.Server
            }
            _state.value = resultState
        }
    }

    private fun processGetCurrentLocationWeather() {
        _state.value = WeatherViewState.Loading
        viewModelScope.launch {
            val hasPermissions = locationPermissionsManager.requestPermissions()
            if (!hasPermissions) {
                _state.value = WeatherViewState.Error.Permission
                return@launch
            }

            val resultState = try {
                val (lat, lng) = getCurrentLocationUseCase.invoke()
                val domainWeather = getWeatherUseCase.invoke(lat, lng)
                val weatherModel = weatherMapper.map(domainWeather)
                WeatherViewState.WeatherData(weatherModel)
            } catch (throwable: Throwable) {
                if (throwable is IOException)
                    WeatherViewState.Error.Network
                else
                    WeatherViewState.Error.Server
            }

            _state.value = resultState
        }
    }
}