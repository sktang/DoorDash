package com.doordash.kitty.doordash

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.doordash.kitty.doordash.RestaurantPageActivity.EXTRA_RESTAURANT_ID
import com.doordash.kitty.doordash.Retrofit.Restaurant
import com.squareup.picasso.Picasso

class RestaurantItemAdapter(private val restaurantList: List<Restaurant>) : RecyclerView.Adapter<RestaurantItemAdapter.RestaurantItemViewHolder>() {

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

    class RestaurantItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var currentRestaurant: Restaurant? = null
        private val rootView: CardView = view.findViewById(R.id.root_view)
        val name: TextView = view.findViewById(R.id.restaurant_item_name)
        private val thumbnail: ImageView = view.findViewById(R.id.restaurant_item_image)
        private val description: TextView = view.findViewById(R.id.restaurant_item_description)
        private val status: TextView = view.findViewById(R.id.restaurant_item_status)

        fun bind(restaurant: Restaurant) {
            currentRestaurant = restaurant
            name.text = restaurant.name
            val imageUri = restaurant.cover_img_url
            Picasso.with(thumbnail.context).load(imageUri).into(thumbnail)
            description.text = restaurant.description
            status.text = restaurant.status

            rootView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val addContactIntent = Intent(v.context, RestaurantPageActivity::class.java)
            addContactIntent.putExtra(EXTRA_RESTAURANT_ID, currentRestaurant?.id)
            v.context.startActivity(addContactIntent)
        }
    }
}

