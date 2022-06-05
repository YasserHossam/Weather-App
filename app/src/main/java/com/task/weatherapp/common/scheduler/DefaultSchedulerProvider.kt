package com.task.weatherapp.common.scheduler

import kotlinx.coroutines.Dispatchers

object DefaultSchedulerProvider : SchedulerProvider {
    override fun main() = Dispatchers.Main

    override fun io() = Dispatchers.IO
}