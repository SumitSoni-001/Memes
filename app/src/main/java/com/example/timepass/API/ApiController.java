package com.example.timepass.API;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        if (retrofit == null)
        {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://meme-api.herokuapp.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
