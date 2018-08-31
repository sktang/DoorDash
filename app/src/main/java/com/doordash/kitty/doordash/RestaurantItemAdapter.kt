package com.doordash.kitty.doordash

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.doordash.kitty.doordash.RestaurantPageActivity.EXTRA_RESTAURANT_ID
import com.doordash.kitty.doordash.Retrofit.Restaurant
import com.squareup.picasso.Picasso

class RestaurantItemAdapter(private val restaurantList: List<Restaurant>, private val context: Context) : RecyclerView.Adapter<RestaurantItemAdapter.RestaurantItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RestaurantItemViewHolder(layoutInflater.inflate(R.layout.restaurant_list_item_layout, parent, false), context)
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(viewHolder: RestaurantItemViewHolder, position: Int) {
        val restaurant: Restaurant = restaurantList[position]
        viewHolder.bind(restaurant)
    }

    class RestaurantItemViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var currentRestaurant: Restaurant? = null
        private val rootView: CardView = view.findViewById(R.id.root_view)
        val name: TextView = view.findViewById(R.id.restaurant_item_name)
        private val thumbnail: ImageView = view.findViewById(R.id.restaurant_item_image)
        private val description: TextView = view.findViewById(R.id.restaurant_item_description)
        private val status: TextView = view.findViewById(R.id.restaurant_item_status)
        private val favoriteIndicator: CheckBox = view.findViewById(R.id.favorite_checkbox)
        private val context: Context = context
        private var isFavorite: Boolean = false

        fun bind(restaurant: Restaurant) {
            currentRestaurant = restaurant
            name.text = restaurant.name
            val imageUri = restaurant.cover_img_url
            Picasso.with(thumbnail.context).load(imageUri).into(thumbnail)
            description.text = restaurant.description
            status.text = restaurant.status
            if (Helper.getFavorites(context) != null) {
                isFavorite = Helper.isFavorited(restaurant, context)
            }
            favoriteIndicator.isChecked = isFavorite
            favoriteIndicator.setOnClickListener(this)

            rootView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (v.id == R.id.favorite_checkbox) {
                if (isFavorite) {
                    Helper.removeFavorite(context, currentRestaurant)
                } else {
                    Helper.addFavorite(context, currentRestaurant)
                }
            } else {
                val addContactIntent = Intent(v.context, RestaurantPageActivity::class.java)
                addContactIntent.putExtra(EXTRA_RESTAURANT_ID, currentRestaurant?.id)
                v.context.startActivity(addContactIntent)
            }
        }
    }
}

