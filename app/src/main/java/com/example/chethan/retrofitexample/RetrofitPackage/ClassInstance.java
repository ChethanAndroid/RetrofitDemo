package com.example.chethan.retrofitexample.RetrofitPackage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassInstance {

    public static final String BASE_URL = "https://reqres.in";

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
