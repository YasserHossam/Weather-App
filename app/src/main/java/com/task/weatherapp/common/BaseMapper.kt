package com.task.weatherapp.common

abstract class BaseMapper<I, O> {
    abstract fun map(input: I): O

    fun map(inputs: List<I>): List<O> {
        return inputs.map { map(it) }
    }
}