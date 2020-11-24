package com.vershininivan.weather.apadters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vershininivan.weather.R
import com.vershininivan.weather.items.FavoriteListItem

class FavoriteListAdapter(private var items: List<FavoriteListItem>) :
    RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteListAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.favorite_list,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteListAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val city: TextView = itemView.findViewById(R.id.city)

        fun bindItems(item: FavoriteListItem) {
            city.text = item.city
        }
    }
}