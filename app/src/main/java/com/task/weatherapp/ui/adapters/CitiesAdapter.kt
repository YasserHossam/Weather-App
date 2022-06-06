package com.task.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.weatherapp.R
import com.task.weatherapp.databinding.ItemCityBinding
import com.task.weatherapp.ui.model.CityModel

class CitiesAdapter(private val onItemClick: (CityModel) -> Unit) :
    ListAdapter<CityModel, CitiesAdapter.CityViewHolder>(CitiesDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CityViewHolder(ItemCityBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CityViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onItemClick(getItem(adapterPosition)) }
        }

        fun bind(model: CityModel) {
            binding.tvLocation.text =
                itemView.context.getString(R.string.item_city_name, model.name, model.countryCode)
        }
    }
}