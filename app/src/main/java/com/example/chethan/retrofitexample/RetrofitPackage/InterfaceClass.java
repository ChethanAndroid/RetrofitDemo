package com.example.chethan.retrofitexample.RetrofitPackage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceClass {


    @GET("/api/unknown")
    Call<ModelClass>getModel();



    @POST("/api/users")
    Call<PostUser>POST_USER_CALL(@Body PostUser postUser);


    @GET("/api/users?")
    Call<UserClass>USER_CLASS_CALL(@Query("page")String page);


    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserClass> CALL_LIST(@Field("name") String name,@Field("job")String job);


}
