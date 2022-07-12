package com.example.retrofitassignment.network;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitAPI {
    private static RetrofitAPI instance;

    public RetrofitAPIService mRetrofitAPIService;
    private RetrofitAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(RetrofitAPIService.BASE_URL)
                .build();

        mRetrofitAPIService = retrofit.create(RetrofitAPIService.class);
    }

    public static RetrofitAPI getInstance() {
        synchronized (RetrofitAPI.class) {
            if (instance == null) {
                instance = new RetrofitAPI();
                return instance;
            }
        }
        return instance;
    }
}
