package com.doordash.kitty.doordash

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class RestaurantItemAdapter(val restaurantList: List<Restaurant>) : RecyclerView.Adapter<RestaurantItemAdapter.RestaurantItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RestaurantItemViewHolder(layoutInflater.inflate(R.layout.restaurant_list_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(viewHolder: RestaurantItemViewHolder, position: Int) {
        val restaurant: Restaurant = restaurantList[position]
        viewHolder.bind(restaurant)
    }

    class RestaurantItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.restaurant_item_image)
        val name: TextView = view.findViewById(R.id.restaurant_item_name)
        val description: TextView = view.findViewById(R.id.restaurant_item_description)
        val status: TextView = view.findViewById(R.id.restaurant_item_status)

        fun bind(restaurant: Restaurant) {
            thumbnail.setImageURI(Uri.parse(restaurant.cover_img_url))
            name.text = restaurant.name
            description.text = restaurant.description
            status.text = restaurant.status
        }
    }
}

