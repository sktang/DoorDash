package com.doordash.kitty.doordash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doordash.kitty.doordash.Retrofit.ApiService;
import com.doordash.kitty.doordash.Retrofit.Restaurant;
import com.doordash.kitty.doordash.Retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.restaurant_list_fragment_layout, container);

        final RecyclerView recyclerView = rootView.findViewById(R.id.restaurant_list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<List<Restaurant>> call = service.getAllRestaurants(37.422740, -122.139956,0, 50);
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if(response.body() != null) {
                    RestaurantItemAdapter restaurantItemAdapter = new RestaurantItemAdapter(response.body(), getActivity());
                    recyclerView.setAdapter(restaurantItemAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(requireContext(), "restaurants GET failed", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
