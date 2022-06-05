package com.task.weatherapp.common.di

import com.task.weatherapp.common.scheduler.DefaultSchedulerProvider
import com.task.weatherapp.common.scheduler.SchedulerProvider
import com.task.weatherapp.data.api.CitiesApi
import com.task.weatherapp.data.api.WeatherApi
import com.task.weatherapp.data.mapper.CityRemoteMapper
import com.task.weatherapp.data.mapper.WeatherRemoteMapper
import com.task.weatherapp.data.repo.DefaultCitiesRepository
import com.task.weatherapp.data.repo.DefaultLocationRepository
import com.task.weatherapp.data.repo.DefaultWeatherRepository
import com.task.weatherapp.domain.repo.CitiesRepository
import com.task.weatherapp.domain.repo.LocationRepository
import com.task.weatherapp.domain.repo.WeatherRepository
import com.task.weatherapp.domain.usecase.GetCurrentLocationUseCase
import com.task.weatherapp.domain.usecase.GetWeatherUseCase
import com.task.weatherapp.domain.usecase.SearchCitiesUseCase
import com.task.weatherapp.ui.activities.WeatherActivity
import com.task.weatherapp.ui.mapper.CityModelMapper
import com.task.weatherapp.ui.mapper.WeatherModelMapper
import com.task.weatherapp.ui.utils.LocationPermissionsManager
import com.task.weatherapp.ui.utils.PermissionsManager
import com.task.weatherapp.ui.viewmodel.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {
    single<SchedulerProvider> { DefaultSchedulerProvider }
}

val weatherModule = module {
    scope<WeatherActivity> {

// Helpers

        scoped<PermissionsManager> { LocationPermissionsManager(get<WeatherActivity>()) }

// Apis

        factory<CitiesApi> {
            val retrofit: Retrofit = get(Retrofit::class.java)
            retrofit.create(CitiesApi::class.java)
        }


        factory<WeatherApi> {
            val retrofit: Retrofit = get(Retrofit::class.java)
            retrofit.create(WeatherApi::class.java)
        }

// Repos

        factory { CityRemoteMapper }
        factory<CitiesRepository>() {
            DefaultCitiesRepository(get(), get())
        }

        factory { WeatherRemoteMapper }
        factory<WeatherRepository>() {
            DefaultWeatherRepository(get(), get())
        }

        factory<LocationRepository>() {
            DefaultLocationRepository(androidContext())
        }

// Use cases

        factory { GetCurrentLocationUseCase(get(), get()) }

        factory { GetWeatherUseCase(get(), get()) }

        factory { SearchCitiesUseCase(get(), get()) }

// UI Mappers

        factory { CityModelMapper }
        factory { WeatherModelMapper }

// ViewModel

        viewModel {
            WeatherViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get()
            )
        }
    }
}
