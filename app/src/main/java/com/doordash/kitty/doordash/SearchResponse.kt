package com.doordash.kitty.doordash

data class Restaurant(
        val name: String,
        val phone_number: String,
        val id: Long,
        val status: String,
        val description: String,
        val delivery_fee: Double,
        val cover_img_url: String,
        val address: Address?
)

data class Result (val total_count: Int, val incomplete_results: Boolean, val items: List<Restaurant>)

data class Address(val street: String, val city: String, val state: String,
                   val country: String, val zip_code: Int,
                   val printable_address: String,
                   val lat: Double, val lng: Double)