/*
package com.task.weatherapp.common.di

import com.mtm.uber_mimic.data.destinations.FoursquareApi
import com.mtm.uber_mimic.data.destinations.FoursquareLocationRepository
import com.mtm.uber_mimic.data.destinations.mappers.DefaultFoursquareLocationMapper
import com.mtm.uber_mimic.data.destinations.mappers.FoursquareLocationMapper
import com.mtm.uber_mimic.data.drivers.FirestoreDriverRepository
import com.mtm.uber_mimic.data.drivers.mappers.DefaultFirestoreDriverMapper
import com.mtm.uber_mimic.data.drivers.mappers.FirestoreDriverMapper
import com.mtm.uber_mimic.data.sources.FirestoreLocationRepository
import com.mtm.uber_mimic.data.sources.mappers.DefaultFirestoreLocationMapper
import com.mtm.uber_mimic.data.sources.mappers.FirestoreLocationMapper
import com.mtm.uber_mimic.domain.repo.DriversRepository
import com.mtm.uber_mimic.domain.repo.LocationRepository
import com.mtm.uber_mimic.domain.usecase.*
import com.task.weatherapp.common.scheduler.DefaultSchedulerProvider
import com.task.weatherapp.common.scheduler.SchedulerProvider
import com.mtm.uber_mimic.ui.activities.RequestRideActivity
import com.mtm.uber_mimic.ui.utils.helper.PermissionHelper
import com.mtm.uber_mimic.ui.models.mappers.DefaultDriverModelMapper
import com.mtm.uber_mimic.ui.models.mappers.DefaultLocationModelMapper
import com.mtm.uber_mimic.ui.models.mappers.DriverModelMapper
import com.mtm.uber_mimic.ui.models.mappers.LocationModelMapper
import com.mtm.uber_mimic.ui.viewmodels.RequestRideViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {
    single<SchedulerProvider> { DefaultSchedulerProvider }
}

val requestRideModule = module {
    val sourceRepoName = "source_repo"
    val destinationRepoName = "destination_repo"

    val sourcesUseCase = "sources_use_case"
    val destinationsUseCase = "destinations_use_case"

    scope<RequestRideActivity> {

        */
/* Helpers *//*

        scoped { PermissionHelper(get<RequestRideActivity>()) }

        */
/* Apis *//*

        factory<FoursquareApi> {
            val retrofit: Retrofit = get(Retrofit::class.java)
            retrofit.create(FoursquareApi::class.java)
        }

        */
/* Repos *//*

        factory<FirestoreLocationMapper> { DefaultFirestoreLocationMapper }
        factory<LocationRepository>(named(sourceRepoName)) {
            FirestoreLocationRepository(androidContext(), get(), get())
        }

        factory<FirestoreDriverMapper> { DefaultFirestoreDriverMapper }
        factory<DriversRepository> { FirestoreDriverRepository(get()) }

        factory<FoursquareLocationMapper> { DefaultFoursquareLocationMapper }
        factory<LocationRepository>(named(destinationRepoName)) {
            FoursquareLocationRepository(get(), androidContext(), get(), get())
        }

        */
/* Use cases *//*

        factory<GetLocationsUseCase>(named(sourcesUseCase)) {
            DefaultGetLocationsUseCase(get(named(sourceRepoName)), get())
        }

        factory<GetLocationsUseCase>(named(destinationsUseCase)) {
            DefaultGetLocationsUseCase(get(named(destinationRepoName)), get())
        }

        factory<GetNearestDriversUseCase> {
            DefaultGetNearestDriversUseCase(get(), get())
        }

        factory<GetCurrentLocationUseCase> {
            DefaultGetCurrentLocationUseCase(get(named(sourceRepoName)), get())
        }

        */
/* UI Mappers *//*

        factory<LocationModelMapper> { DefaultLocationModelMapper }
        factory<DriverModelMapper> { DefaultDriverModelMapper }

        */
/* ViewModel *//*

        viewModel {
            RequestRideViewModel(
                get(),
                get(named(sourcesUseCase)),
                get(named(destinationsUseCase)),
                get(),
                get(),
                get(),
                get()
            )
        }
    }
}*/
