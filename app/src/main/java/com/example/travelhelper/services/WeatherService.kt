package com.example.travelhelper.services

import com.example.travelhelper.utils.ApiKeys
import com.example.travelhelper.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

object WeatherService {

    private val client = OkHttpClient()

    fun getWeatherByCoordinates(latitude: Double, longitude: Double, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        val url = "${Constants.WEATHER_API_BASE_URL}weather?lat=$latitude&lon=$longitude&appid=${ApiKeys.OPENWEATHER_API_KEY}&units=metric&lang=pt"

        val request = Request.Builder().url(url).build()

        client.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                response.body?.let { body ->
                    val jsonResponse = JSONObject(body.string())
                    val weatherDescription = jsonResponse.getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("description")

                    val temperature = jsonResponse.getJSONObject("main").getDouble("temp")

                    val result = "Clima: $weatherDescription, Temperatura: $temperature°C"
                    onSuccess(result)
                } ?: onFailure(Exception("Resposta vazia da API"))
            } else {
                onFailure(Exception("Falha na solicitação: ${response.code}"))
            }
        }
    }
}
