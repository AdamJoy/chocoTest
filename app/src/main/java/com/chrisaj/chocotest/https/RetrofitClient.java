package com.chrisaj.chocotest.https;

import com.chrisaj.chocotest.BuildConfig;
import com.chrisaj.chocotest.model.DramaModel;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 *  Retrofit主頁
 */
public class RetrofitClient {

    private static Retrofit mRetrofit;

    private static final String BASE_URL = BuildConfig.API_SERVER_URL;    // Retrofit domain

    public static DramaDataService getService() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(DramaDataService.class);
    }
}