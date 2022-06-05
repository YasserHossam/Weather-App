package com.task.weatherapp.common.scheduler

import kotlinx.coroutines.CoroutineDispatcher

interface SchedulerProvider {
    fun main(): CoroutineDispatcher

    fun io(): CoroutineDispatcher
}