package com.example.shoopeeapplication.Network

import com.example.shoopeeapplication.model.DataUsersPostItem
import com.example.shoopeeapplication.model.DataUsers
import com.example.shoopeeapplication.model.DataUsersResponseItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //Get Sliders

//    //Get News and Detail on Click by Id
//    @GET("news_update")
//    fun getNews(): Call<List<DataNewsResponseItem>>
//    @GET("news_update/{id}?")
//    fun getDetailNews(@Path("id") id:Int): Call<DataDetailNewsItem>


    //Get User and All Data User for profile
    @GET("users")
    fun getAllUser(): Call<List<DataUsersResponseItem>>

    @POST("users")
    fun registerUser(@Body request: DataUsers): Call<List<DataUsersPostItem>>

//    @PUT("users/{id}")
//    fun updateUser(@Path("id") id : Int, @Body request: DataProfile): Call<PostUserResponse>

}