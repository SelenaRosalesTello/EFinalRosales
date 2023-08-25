package com.selena.efinalrosalestello.data.retrofit;

import com.selena.efinalrosalestello.data.response.ShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CatInterface {
    @GET("198c5b07-ca0b-418f-acfe-4d7248d2c97d")
    Call<ShowResponse> getShow();
}
