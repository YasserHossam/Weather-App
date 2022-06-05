package com.task.weatherapp.common.di

import com.task.weatherapp.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkingModule = module {

    val authenticationInterceptorName = "auth_interceptor"
    val loggingInterceptorName = "logging_interceptor"

    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    single(named(authenticationInterceptorName)) {
        val apiKeyName = "appid"
        val apiKey = androidContext().getString(R.string.weather_api_key)

        Interceptor { chain ->
            chain.run {
                val newUrl = chain.request().url().newBuilder()
                    .addQueryParameter(apiKeyName, apiKey)
                    .build()
                proceed(
                    request()
                        .newBuilder()
                        .url(newUrl)
                        .build()
                )
            }
        }
    }

    single<Interceptor>(named(loggingInterceptorName)) {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get(named(authenticationInterceptorName)))
            .addInterceptor(get(named(loggingInterceptorName)))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(androidContext().getString(R.string.weather_api_base_url))
            .addConverterFactory(get())
            .client(get())
            .build()
    }
}