package com.doordash.kitty.doordash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.doordash.kitty.doordash.Retrofit.ApiService;
import com.doordash.kitty.doordash.Retrofit.Restaurant;
import com.doordash.kitty.doordash.Retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantPageActivity extends AppCompatActivity {

    public static final String EXTRA_RESTAURANT_ID = "restaurantIDExtra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.restaurant_page_layout);
        Long restaurantId = (Long) getIntent().getSerializableExtra(EXTRA_RESTAURANT_ID);

        ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<Restaurant> call = service.getRestaurant(restaurantId);
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                Restaurant restaurant = response.body();
                if (restaurant != null) {
                    TextView name = findViewById(R.id.restaurant_page_name);
                    TextView description = findViewById(R.id.restaurant_page_description);
                    TextView status = findViewById(R.id.restaurant_page_status);
                    TextView deliveryFee = findViewById(R.id.restaurant_page_delivery_fee);

                    name.setText(restaurant.getName());
                    description.setText(restaurant.getDescription());
                    status.setText(restaurant.getStatus());
                    deliveryFee.setText(String.format(getResources().getString(R.string.delivery_fee), String.valueOf(restaurant.getDelivery_fee())));
                } else {
                    Toast.makeText(RestaurantPageActivity.this, "restaurant fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Toast.makeText(RestaurantPageActivity.this, "restaurant GET failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
