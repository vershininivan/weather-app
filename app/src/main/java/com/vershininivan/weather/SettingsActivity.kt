package com.vershininivan.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.vershininivan.weather.apadters.FavoriteListAdapter
import com.vershininivan.weather.items.FavoriteListItem

class SettingsActivity: AppCompatActivity() {

    private val favorites: MutableList<FavoriteListItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setInitialData()
        initRecycler()
    }

    private fun setInitialData() {
        favorites.add(FavoriteListItem("Ivanovo", 57.00, 41.00))
        favorites.add(FavoriteListItem("Moscow", 55.7558, 37.6178))
        favorites.add(FavoriteListItem("Saint Petersburg", 59.95, 30.3167))
    }

    private fun initRecycler() {
        val favoriteRecycler: RecyclerView = findViewById(R.id.favorite)
        val adapterFavorite = FavoriteListAdapter(favorites)
        favoriteRecycler.adapter = adapterFavorite
    }
}