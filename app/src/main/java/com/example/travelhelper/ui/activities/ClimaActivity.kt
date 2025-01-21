package com.example.travelhelper.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.travelhelper.R
import com.example.travelhelper.services.WeatherService
import com.example.travelhelper.utils.LocationUtils

class ClimaActivity : AppCompatActivity() {

    private lateinit var txtWeatherInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)

        txtWeatherInfo = findViewById(R.id.txtWeatherInfo)

        LocationUtils.getUserLocation(
            context = this,
            onSuccess = { location ->
                if (location != null) {
                    WeatherService.getWeatherByCoordinates(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        onSuccess = { weatherInfo ->
                            runOnUiThread {
                                txtWeatherInfo.text = weatherInfo
                            }
                        },
                        onFailure = { exception ->
                            runOnUiThread {
                                txtWeatherInfo.text = "Erro ao obter clima: ${exception.message}"
                            }
                        }
                    )
                }
            },
            onFailure = { exception ->
                txtWeatherInfo.text = "Erro ao obter localização: ${exception.message}"
            }
        )
    }
}
