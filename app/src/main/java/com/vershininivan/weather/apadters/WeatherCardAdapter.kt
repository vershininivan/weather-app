package com.vershininivan.weather.apadters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vershininivan.weather.R
import com.vershininivan.weather.items.WeatherCardItem

class WeatherCardAdapter(var items: List<WeatherCardItem>) :
    RecyclerView.Adapter<WeatherCardAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherCardAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_list,
                parent, false
            )
        )
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bindItems(items[position])
        val parent = items[position]
        holder.city.text = parent.city
        holder.temp.text = parent.temp
        holder.description.text = parent.description
        val childLayoutManager = LinearLayoutManager(holder.forecast.context, LinearLayout.VERTICAL, false)
        holder.forecast.apply {
            layoutManager = childLayoutManager
            adapter = WeatherCardForecastAdapter(parent.forecastItem)
            setRecycledViewPool(viewPool)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val city: TextView = item.findViewById(R.id.city)
        val temp: TextView = item.findViewById(R.id.temp)
        val description: TextView = item.findViewById(R.id.description)
        val forecast: RecyclerView = item.findViewById(R.id.forecast)

//        fun bindItems(item: WeatherCardItem) {
//            city.text = item.city
//            temp.text = item.temp
//            description.text = item.description
//            //forecast.adapter = item.forecastItem
//        }

    }

}