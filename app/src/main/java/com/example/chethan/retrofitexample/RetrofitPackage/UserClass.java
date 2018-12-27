package com.example.chethan.retrofitexample.RetrofitPackage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserClass {

    @SerializedName("page")
    public String Page;

    @SerializedName("per_page")
    public String Per_page;

    @SerializedName("total")
    public String Total;

    @SerializedName("total_pages")
    public String Total_pages;

    @SerializedName("data")
    public List<arraydata> Data;


    public class arraydata{

        @SerializedName("id")
        public String Id;

        @SerializedName("first_name")
        public String First_name;

        @SerializedName("last_name")
        public String Last_name;

        @SerializedName("avatar")
        public String Avatar;
    }

}
