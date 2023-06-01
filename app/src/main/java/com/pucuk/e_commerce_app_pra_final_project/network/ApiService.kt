package com.pucuk.e_commerce_app_pra_final_project.network

import com.pucuk.e_commerce_app_pra_final_project.model.users_response.DataUsers
import com.pucuk.e_commerce_app_pra_final_project.model.users_response.DataUsersPostItem
import com.pucuk.e_commerce_app_pra_final_project.model.users_response.DataUsersResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.cart_response.DataCart
import com.pucuk.e_commerce_app_pra_final_project.model.cart_response.DataCartResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.category_product_response.DataCategoryProductResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.favourite_response.DataFavourite
import com.pucuk.e_commerce_app_pra_final_project.model.favourite_response.DataFavouriteResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.news_response.DataDetailNewsItem
import com.pucuk.e_commerce_app_pra_final_project.model.news_response.DataNewsResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.product_response.DataProductsResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.transaction_history_response.DataHistoryTransactionResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.transaction_history_response.DataTransactionHistory

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //Get Sliders

    //Get News and Detail on Click by Id
    @GET("news_update")
    fun getNews(): Call<List<DataNewsResponseItem>>
    @GET("news_update/{id}?")
    fun getDetailNews(@Path("id") id:Int): Call<DataDetailNewsItem>

    //Get User and All Data User for profile
    @GET("users")
    fun getAllUser(): Call<List<DataUsersResponseItem>>

    @POST("users")
    fun registerUser(@Body request: DataUsers): Call<List<DataUsersPostItem>>

//    @PUT("users/{id}")
//    fun updateUser(@Path("id") id : Int, @Body request: DataProfile): Call<PostUserResponse>

    //category product
    @GET("/category_product")
    fun getAllCategory(): Call<List<DataCategoryProductResponseItem>>

    //products
    @GET("/category_product/{id}/products")
    fun getAllProductsByCategory(
        @Path("id") id:Int
    ) : Call<List<DataProductsResponseItem>>

    @GET("/category_product/{id}/products/{id_product}")
    fun getDetailProduct(
        @Path("id") id:Int,
        @Path("id_product") id_product:Int
    ) : Call<List<DataProductsResponseItem>>
    

    //Cart
    @GET("users/{userId}/cart")
    fun getAllCartUserById(
        @Path("userId") userId:Int
    ): Call<List<DataCartResponseItem>>

    @POST("users/{userId}/cart")
    fun postCartUser(
        @Path("userId") userId:Int,
        @Body request: DataCart
    ) : Call<List<DataCartResponseItem>>

    @DELETE("users/{userId}/cart/{id_cart}")
    fun deleteCartItemUser(
        @Path("userId") userId: Int,
        @Path("id_cart") cartId: Int
    ) : Call<Int>


    //History Transaction
    @GET("users/{userId}/transhistory")
    fun getAllHistoryTransactionUserById(
        @Path("userId") userId:Int
    ): Call<List<DataHistoryTransactionResponseItem>>

    @POST("users/{userId}/transhistory")
    fun postHistoryTransactionUser(
        @Path("userId") userId:Int,
        @Body request: DataTransactionHistory
    ) : Call<List<DataHistoryTransactionResponseItem>>

    @DELETE("users/{userId}/transhistory/{id_trans}")
    fun deleteHistoryTransactionItemUser(
        @Path("userId") userId: Int,
        @Path("id_trans") transId: Int
    ) : Call<Int>


    //Favourite
    @GET("users/{userId}/favourite")
    fun getAllFavouriteProductUserById(
        @Path("userId") userId:Int
    ): Call<List<DataFavouriteResponseItem>>

    @POST("users/{userId}/favourite")
    fun postFavouriteProductUser(
        @Path("userId") userId:Int,
        @Body request: DataFavourite
    ) : Call<List<DataFavouriteResponseItem>>

    @DELETE("users/{userId}/favourite/{id_fav}")
    fun deleteFavouriteProductItemUser(
        @Path("userId") userId: Int,
        @Path("id_fav") favId: Int
    ) : Call<Int>

}