package com.example.travelhelper.utils

object Constants {
    // URLs da API de clima e localização
    const val WEATHER_API_BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val COUNTRY_LANGUAGE_API = "https://restcountries.com/v3.1/all"

    // Chaves para SharedPreferences
    const val PREFS_NAME = "LanguageHelperPrefs"
    const val PREFS_USER_LOGGED_IN = "user_logged_in"
    const val PREFS_USERNAME = "username"

    // Códigos de idioma padrão
    const val DEFAULT_LANGUAGE_CODE = "en"
    const val FALLBACK_LANGUAGE_CODE = "pt"

    // Limites de requisições
    const val LOCATION_UPDATE_INTERVAL: Long = 5000  // 5 segundos
    const val LOCATION_FASTEST_INTERVAL: Long = 2000  // 2 segundos
}
