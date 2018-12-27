package com.example.chethan.retrofitexample.RetrofitPackage;

import com.google.gson.annotations.SerializedName;

public class PostUser {

    @SerializedName("name")
    public String Name;

    @SerializedName("id")
    public String Id;

    @SerializedName("job")
    public String Job;

    @SerializedName("createdAt")
    public String CreatedAt;


    public PostUser(String Name,String Job){
        this.Name = Name;
        this.Job = Job;
    }
}
