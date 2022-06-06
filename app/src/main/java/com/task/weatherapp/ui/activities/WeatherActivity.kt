package com.task.weatherapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.weatherapp.R
import com.task.weatherapp.databinding.ActivityWeatherBinding
import com.task.weatherapp.ui.adapters.CitiesAdapter
import com.task.weatherapp.ui.model.CityModel
import com.task.weatherapp.ui.model.WeatherModel
import com.task.weatherapp.ui.utils.closeKeyboard
import com.task.weatherapp.ui.utils.gone
import com.task.weatherapp.ui.utils.show
import com.task.weatherapp.ui.utils.textChanges
import com.task.weatherapp.ui.viewmodel.WeatherIntent
import com.task.weatherapp.ui.viewmodel.WeatherViewModel
import com.task.weatherapp.ui.viewmodel.WeatherViewState
import kotlinx.coroutines.flow.*
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class WeatherActivity : AppCompatActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()

    private val viewModel: WeatherViewModel by viewModel()

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        setupObservers()
        setupListeners()
        viewModel.processIntent(WeatherIntent.GetCurrentLocationWeather)
    }

    private fun setupRecycler() {
        binding.recyclerCities.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val adapter = CitiesAdapter {
            viewModel.processIntent(WeatherIntent.GetWeather(it))
            binding.recyclerCities.gone()
            closeKeyboard()
        }
        binding.recyclerCities.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.state
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)
    }

    private fun setupListeners() {
        binding.editCity.textChanges()
            .debounce(500)
            .onEach { viewModel.processIntent(WeatherIntent.SearchCities(it.toString())) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: WeatherViewState) {
        when (state) {
            is WeatherViewState.CitiesData -> handleCitiesState(state.cities)
            is WeatherViewState.WeatherData -> handleWeatherState(state.weather)
            WeatherViewState.Error.Network -> {}
            WeatherViewState.Error.Permission -> {}
            WeatherViewState.Error.Server -> {}
            WeatherViewState.Idle -> {}
            WeatherViewState.Loading -> {}
        }
    }

    private fun handleCitiesState(cities: List<CityModel>) {
        binding.recyclerCities.show()
        binding.layoutWeatherDetails.gone()
        (binding.recyclerCities.adapter as? CitiesAdapter)?.submitList(cities)
    }

    private fun handleWeatherState(weather: WeatherModel) {
        binding.recyclerCities.gone()
        binding.layoutWeatherDetails.show()
        binding.textTemperature.text = getString(R.string.activity_weather_temperature_value, weather.temperature.toInt())
        binding.textMainWeather.text = weather.weatherState
        binding.textPressure.text = weather.pressure.toString()
        binding.textHumidity.text = getString(R.string.activity_weather_humidity_value, weather.humidity.toInt())
        binding.textClouds.text = getString(R.string.activity_weather_clouds_value, weather.clouds.toInt())
        binding.textWindSpeed.text = getString(R.string.activity_weather_wind_speed_value, weather.windSpeed)
    }

}