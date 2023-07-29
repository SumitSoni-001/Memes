package com.example.timepass.API;

import com.example.timepass.Models.ApiModel;
import com.example.timepass.Models.MemeModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("gimme/100")
    Call<MemeModel> getMeme();

}
