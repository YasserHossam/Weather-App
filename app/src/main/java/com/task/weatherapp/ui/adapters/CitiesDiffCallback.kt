package com.task.weatherapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.task.weatherapp.ui.model.CityModel

object CitiesDiffCallback : DiffUtil.ItemCallback<CityModel>() {
    override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
        return oldItem.name == newItem.name && oldItem.countryCode == newItem.countryCode
    }
}