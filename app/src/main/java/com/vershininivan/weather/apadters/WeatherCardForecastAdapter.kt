package com.vershininivan.weather.apadters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vershininivan.weather.R
import com.vershininivan.weather.items.WeatherCardForecastItem

class WeatherCardForecastAdapter(private var items: List<WeatherCardForecastItem>) :
    RecyclerView.Adapter<WeatherCardForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherCardForecastAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.forecast_list,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherCardForecastAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dayOfWeek: TextView = itemView.findViewById(R.id.day_of_week)
        private val tempMin: TextView = itemView.findViewById(R.id.temp_min)
        private val tempMax: TextView = itemView.findViewById(R.id.temp_max)
        private val forecastIcon: TextView = itemView.findViewById(R.id.forecast_icon)

        fun bindItems(item: WeatherCardForecastItem) {
            dayOfWeek.text = item.dayOfWeek
            tempMin.text = item.tempMin
            tempMax.text = item.tempMax
            forecastIcon.text = item.icon
        }
    }

}