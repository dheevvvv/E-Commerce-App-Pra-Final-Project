package com.example.shoopeeapplication.Network

import com.pucuk.e_commerce_app_pra_final_project.model.DataUsersPostItem
import com.pucuk.e_commerce_app_pra_final_project.model.DataUsers
import com.pucuk.e_commerce_app_pra_final_project.model.DataUsersResponseItem
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


    //Cart
    @GET("users/{id}/cart")
    fun getAllCartUserById()

}