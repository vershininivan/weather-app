package com.vershininivan.weather

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vershininivan.weather.apadters.FavoriteListAdapter
import com.vershininivan.weather.items.FavoriteListItem


class SettingsActivity: AppCompatActivity() {

    private val favorites: MutableList<FavoriteListItem> = ArrayList()

    //val jsonFileString = getJsonDataFromAsset(applicationContext, "cities.json")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val allCities = getAllCities()
        setInitialData(allCities)
        RecyclerManager().initRecycler()
    }

    private fun setInitialData(allCities: List<FavoriteListItem>) {
        for (city in allCities) {
            favorites.add(
                FavoriteListItem(
                    city.city,
                    city.city_ascii,
                    city.country,
                    city.lat,
                    city.lon
                )
            )
        }
    }

    private fun getAllCities(): List<FavoriteListItem> {
        val jsonCitiesString: String = "[{\"city\":\"Saint Petersburg\",\"city_ascii\":\"Saint Petersburg\",\"lat\":59.95,\"lon\":30.3167,\"country\":\"Russia\"},{\"city\":\"Ivanovo\",\"city_ascii\":\"Ivanovo\",\"lat\":57,\"lon\":41,\"country\":\"Russia\"},{\"city\":\"Moscow\",\"city_ascii\":\"Moscow\",\"lat\":55.7558,\"lon\":37.6178,\"country\":\"Russia\"},{\"city\":\"Bangkok\",\"city_ascii\":\"Bangkok\",\"lat\":13.75,\"lon\":100.5167,\"country\":\"Thailand\"},{\"city\":\"New York\",\"city_ascii\":\"New York\",\"lat\":40.6943,\"lon\":-73.9249,\"country\":\"United States\"},{\"city\":\"Beijing\",\"city_ascii\":\"Beijing\",\"lat\":39.905,\"lon\":116.3914,\"country\":\"China\"},{\"city\":\"Tokyo\",\"city_ascii\":\"Tokyo\",\"lat\":35.6897,\"lon\":139.6922,\"country\":\"Japan\"},{\"city\":\"Manila\",\"city_ascii\":\"Manila\",\"lat\":14.5958,\"lon\":120.9772,\"country\":\"Philippines\"},{\"city\":\"São Paulo\",\"city_ascii\":\"Sao Paulo\",\"lat\":-23.5504,\"lon\":-46.6339,\"country\":\"Brazil\"},{\"city\":\"Seoul\",\"city_ascii\":\"Seoul\",\"lat\":37.5833,\"lon\":127,\"country\":\"Korea, South\"},{\"city\":\"Mexico City\",\"city_ascii\":\"Mexico City\",\"lat\":19.4333,\"lon\":-99.1333,\"country\":\"Mexico\"},{\"city\":\"Buenos Aires\",\"city_ascii\":\"Buenos Aires\",\"lat\":-34.5997,\"lon\":-58.3819,\"country\":\"Argentina\"},{\"city\":\"Dhaka\",\"city_ascii\":\"Dhaka\",\"lat\":23.7161,\"lon\":90.3961,\"country\":\"Bangladesh\"},{\"city\":\"Lagos\",\"city_ascii\":\"Lagos\",\"lat\":6.45,\"lon\":3.4,\"country\":\"Nigeria\"},{\"city\":\"Istanbul\",\"city_ascii\":\"Istanbul\",\"lat\":41.01,\"lon\":28.9603,\"country\":\"Turkey\"},{\"city\":\"Los Angeles\",\"city_ascii\":\"Los Angeles\",\"lat\":34.1139,\"lon\":-118.4068,\"country\":\"United States\"}]"
        val listCities = object : TypeToken<List<FavoriteListItem>>() {}.type
        return  Gson().fromJson(jsonCitiesString, listCities)
    }

    inner class RecyclerManager() {
        private val favoriteRecycler: RecyclerView = findViewById(R.id.favorite)
        private val adapterFavorite = FavoriteListAdapter(favorites)

        init {
            adapterFavorite.setOnItemClickListener {
                val index = adapterFavorite.items.indexOf(it)
                favorites.removeAt(index)
                update()
            }
        }

        fun initRecycler() {
            favoriteRecycler.adapter = adapterFavorite

        }

        private fun update() {
            favoriteRecycler.adapter!!.notifyDataSetChanged()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("favorites", ArrayList(favorites))
            startActivity(intent)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}