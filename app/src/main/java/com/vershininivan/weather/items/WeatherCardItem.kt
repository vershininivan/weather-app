package com.vershininivan.weather.items

data class WeatherCardItem(
    val city: String?,
    val temp: String,
    val description: String,
    val forecastItem: MutableList<WeatherCardForecastItem>
)