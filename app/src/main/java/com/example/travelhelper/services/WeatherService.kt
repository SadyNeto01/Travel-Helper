package com.example.travelhelper.services

import com.example.travelhelper.utils.ApiKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

object WeatherService {

    private val client = OkHttpClient()
    private const val API_KEY = ApiKeys.OPENWEATHER_API_KEY
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather"

    suspend fun fetchWeatherData(cityName: String): WeatherData? {
        val url = "$BASE_URL?q=$cityName&appid=$API_KEY&units=metric"
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        parseWeatherData(it.string())
                    }
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun parseWeatherData(response: String): WeatherData {
        val jsonObject = JSONObject(response)
        val main = jsonObject.getJSONObject("main")
        val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
        val wind = jsonObject.getJSONObject("wind")

        return WeatherData(
            cityName = jsonObject.getString("name"),
            temperature = main.getDouble("temp"),
            humidity = main.getDouble("humidity"),
            windSpeed = wind.getDouble("speed"),
            description = weather.getString("description"),
            iconCode = weather.getString("icon")
        )
    }
}

data class WeatherData(
    val cityName: String,
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val description: String,
    val iconCode: String
)
