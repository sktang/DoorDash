package com.doordash.kitty.doordash;

import com.doordash.kitty.doordash.Retrofit.Address;
import com.doordash.kitty.doordash.Retrofit.ApiService;
import com.doordash.kitty.doordash.Retrofit.Restaurant;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Matchers.any;

public class ApiTest {

    @Test
    public void testRestaurantsListCall() {
        ApiService mockedApiInterface = Mockito.mock(ApiService.class);
        final Call<List<Restaurant>> mockedCall = Mockito.mock(Call.class);

        Restaurant dummy = new Restaurant("", "", 30, "", "", 0, "", new Address("", "", "", "", 0, "", 0, 0));
        final List<Restaurant> dummyList = Arrays.asList(dummy);

        Mockito.when(mockedApiInterface.getAllRestaurants(37.422740, -122.139956,0, 50)).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<List<Restaurant>> callback = invocation.getArgumentAt(0, Callback.class);

                callback.onResponse(mockedCall, Response.success(dummyList));
                // or callback.onResponse(mockedCall, Response.error(404. ...);
                // or callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
    }

    @Test
    public void testRestaurantCall() {
        ApiService mockedApiInterface = Mockito.mock(ApiService.class);
        final Call<Restaurant> mockedCall = Mockito.mock(Call.class);

        Mockito.when(mockedApiInterface.getRestaurant(30)).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<Restaurant> callback = invocation.getArgumentAt(0, Callback.class);

                callback.onResponse(mockedCall, Response.success(new Restaurant("", "", 30, "", "", 0, "", new Address("", "", "", "", 0, "", 0, 0))));
                // or callback.onResponse(mockedCall, Response.error(404. ...);
                // or callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
    }

}
