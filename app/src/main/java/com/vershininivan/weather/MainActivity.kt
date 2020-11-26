package com.vershininivan.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.vershininivan.weather.apadters.WeatherCardAdapter
import com.vershininivan.weather.data.OneCall
import com.vershininivan.weather.items.FavoriteListItem
import com.vershininivan.weather.items.WeatherCardForecastItem
import com.vershininivan.weather.items.WeatherCardItem

class MainActivity : AppCompatActivity() {

  private val cardList: MutableList<WeatherCardItem> = ArrayList()
  private val forecastList: MutableList<WeatherCardForecastItem> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val setting: ImageButton = findViewById(R.id.settings)

    setting.setOnClickListener {
      val intent = Intent(this, SettingsActivity::class.java)
      startActivity(intent)
    }

    val favoriteList = intent.getParcelableArrayListExtra<FavoriteListItem>("favorites")
    if (favoriteList != null) {
      setFavoriteCity(favoriteList)
    }

    RecyclerManager().initRecycler()
  }

  private fun setFavoriteCity(favoriteListItem: ArrayList<FavoriteListItem>) {
    for ((index, item) in favoriteListItem.withIndex()) {
      request(getForecastWeatherData(item.lat, item.lon), item.city_ascii, index)
    }
  }

  private fun request(url: String, city: String?, cardIndex: Int) {
    url.httpGet().responseObject(OneCall.Deserializer()) { _, _, result ->
      when (result) {
        is Result.Failure -> {
          Toast.makeText(this@MainActivity, result.getException().message,
              Toast.LENGTH_SHORT).show()
        }
        is Result.Success -> {
          val forecast = result.component1()
          if (forecast != null) {
            for ((index, day) in forecast.daily.withIndex()) {
              if (index != 0) {
                forecastList.add(WeatherCardForecastItem(day.dt.toString(), day.temp.min.toString(),
                    day.temp.max.toString(), day.weather.first().icon))
              }
            }
            cardList.add(WeatherCardItem(city, forecast.current.temp.toInt().toString(),
                forecast.current.weather.first().description, ArrayList(forecastList)))
            forecastList.clear()
            RecyclerManager().update()
          }
        }
      }
    }
  }

  private fun getForecastWeatherData(
      lat: Double,
      lon: Double,
      appid: String = "cf387cc3efe8d90d7a2a0dbd64a97e64",
      units: String = "metric",
      lang: String = "ru"): String {
    return "https://api.openweathermap.org/data/2.5/onecall?lat=${lat}&lon=${lon}&appid=${appid}&exclude=minutely,hourly,alerts&units=${units}&lang=${lang}"
  }

  inner class RecyclerManager() {
    private val cardRecycler: RecyclerView = findViewById(R.id.weather_card_recycler)
    private val adapterCurrent = WeatherCardAdapter(cardList)

    fun initRecycler() {
      cardRecycler.adapter = adapterCurrent
    }

    fun update() {
      cardRecycler.adapter!!.notifyDataSetChanged()
    }
  }

//  private fun initRecycler() {
//    val cardRecycler: RecyclerView = findViewById(R.id.weather_card_recycler)
//    val adapterCurrent = WeatherCardAdapter(cards)
//    cardRecycler.adapter = adapterCurrent
//  }
}