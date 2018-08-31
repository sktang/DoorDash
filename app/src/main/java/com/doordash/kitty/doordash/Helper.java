package com.doordash.kitty.doordash;

import android.content.Context;
import android.content.SharedPreferences;

import com.doordash.kitty.doordash.Retrofit.Restaurant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static final String DOOR_DASH_PREF = "DoorDashPref";
    public static final String FAVORITES = "favoritesPref";

    public static void saveFavorites(Context context, List<Restaurant> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(DOOR_DASH_PREF,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public static void addFavorite(Context context, Restaurant restaurant) {
        List<Restaurant> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<>();
        favorites.add(restaurant);
        saveFavorites(context, favorites);
    }

    public static void removeFavorite(Context context, Restaurant product) {
        ArrayList<Restaurant> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(context, favorites);
        }
    }

    public static ArrayList<Restaurant> getFavorites(Context context) {
        SharedPreferences settings;
        List<Restaurant> favorites;

        settings = context.getSharedPreferences(DOOR_DASH_PREF,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Restaurant[] favoriteItems = gson.fromJson(jsonFavorites,
                    Restaurant[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<>(favorites);
        } else
            return null;

        return (ArrayList<Restaurant>) favorites;
    }

    public static boolean isFavorited(Restaurant curRestaurant, Context context) {
        ArrayList<Restaurant> favorites = getFavorites(context);
        for (Restaurant restaurant : favorites) {
            if (Long.valueOf(restaurant.getId()).compareTo(Long.valueOf(curRestaurant.getId())) == 0) {
                return true;
            }
        }
        return false;
    }
}
