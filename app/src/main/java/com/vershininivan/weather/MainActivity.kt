package com.vershininivan.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.vershininivan.weather.apadters.WeatherCardAdapter
import com.vershininivan.weather.items.WeatherCardForecastItem
import com.vershininivan.weather.items.WeatherCardItem

class MainActivity : AppCompatActivity() {

  private val cards: MutableList<WeatherCardItem> = ArrayList()
  private val forecast_1: MutableList<WeatherCardForecastItem> = ArrayList()
  private val forecast_2: MutableList<WeatherCardForecastItem> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setInitialData()
    initRecycler()
  }

  private fun setInitialData() {
    forecast_1.add(WeatherCardForecastItem("Вторник", "4", "12", "l10n"))
    forecast_1.add(WeatherCardForecastItem("Среда", "4", "12", "l10n"))
    forecast_1.add(WeatherCardForecastItem("Четверг", "4", "12", "l10n"))
    forecast_1.add(WeatherCardForecastItem("Пятница", "4", "12", "l10n"))
    forecast_1.add(WeatherCardForecastItem("Суббота", "4", "12", "l10n"))
    forecast_1.add(WeatherCardForecastItem("Воскресенье", "4", "12", "l10n"))
    forecast_2.add(WeatherCardForecastItem("Вторник", "4", "12", "l10n"))
    forecast_2.add(WeatherCardForecastItem("Среда", "4", "12", "l10n"))
    forecast_2.add(WeatherCardForecastItem("Четверг", "4", "12", "l10n"))
    forecast_2.add(WeatherCardForecastItem("Пятница", "4", "12", "l10n"))
    forecast_2.add(WeatherCardForecastItem("Суббота", "4", "12", "l10n"))
    forecast_2.add(WeatherCardForecastItem("Воскресенье", "4", "12", "l10n"))
    cards.add(WeatherCardItem("Иваново", "10°", "Солнечно", forecast_1))
    cards.add(WeatherCardItem("Москва", "10°", "Солнечно", forecast_2))
    cards.add(WeatherCardItem("Санкт-Петербург", "10°", "Солнечно", forecast_1))
  }

  private fun initRecycler() {
    val cardRecycler: RecyclerView = findViewById(R.id.weather_card_recycler)
    val adapterCurrent = WeatherCardAdapter(cards)
    cardRecycler.adapter = adapterCurrent
  }
}