package com.example.travelhelper.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.travelhelper.R
import com.example.travelhelper.services.WeatherData
import com.example.travelhelper.services.WeatherService
import com.example.travelhelper.utils.LocationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClimaActivity : AppCompatActivity() {

    private lateinit var cityNameText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var humidityText: TextView
    private lateinit var windText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var weatherIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)

        cityNameText = findViewById(R.id.cityNameText)
        temperatureText = findViewById(R.id.temperatureText)
        humidityText = findViewById(R.id.humidityText)
        windText = findViewById(R.id.windText)
        descriptionText = findViewById(R.id.descriptionText)
        weatherIcon = findViewById(R.id.weatherIcon)

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }

        fetchWeatherByLocation()
    }

    private fun fetchWeatherByLocation() {
        LocationUtils.startLocationUpdates(this) { location ->
            val cityName = LocationUtils.getCityFromLocation(this, location.latitude, location.longitude)
            if (cityName != null) {
                fetchWeatherData(cityName)
            } else {
                cityNameText.text = "Erro ao obter cidade"
            }
        }
    }

    private fun fetchWeatherData(cityName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val weatherData = WeatherService.fetchWeatherData(cityName)
            if (weatherData != null) {
                updateUI(weatherData)
            } else {
                cityNameText.text = "Erro ao buscar previsão do tempo"
            }
        }
    }

    private fun updateUI(weatherData: WeatherData) {
        cityNameText.text = weatherData.cityName
        temperatureText.text = String.format("%.0f°", weatherData.temperature)
        humidityText.text = String.format("%.0f%%", weatherData.humidity)
        windText.text = String.format("%.0f km/h", weatherData.windSpeed)
        descriptionText.text = weatherData.description.replaceFirstChar { it.uppercaseChar() }

        val resourceName = "ic_${weatherData.iconCode}"
        val resId = resources.getIdentifier(resourceName, "drawable", packageName)
        if (resId != 0) {
            weatherIcon.setImageResource(resId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationUtils.stopLocationUpdates()
    }
}
