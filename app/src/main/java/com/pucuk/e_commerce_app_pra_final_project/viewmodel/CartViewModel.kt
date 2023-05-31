package com.pucuk.e_commerce_app_pra_final_project.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pucuk.e_commerce_app_pra_final_project.model.cart_response.DataCart
import com.pucuk.e_commerce_app_pra_final_project.model.cart_response.DataCartPostResponse
import com.pucuk.e_commerce_app_pra_final_project.model.cart_response.DataCartResponseItem
import com.pucuk.e_commerce_app_pra_final_project.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartViewModel:ViewModel() {
    private val _cart:MutableLiveData<List<DataCartResponseItem>> = MutableLiveData()
    val cart : LiveData<List<DataCartResponseItem>> get() = _cart


    fun callApiCart(userId:Int){
        ApiClient.instance.getAllCartUserById(userId).enqueue(object : Callback<List<DataCartResponseItem>>{
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<List<DataCartResponseItem>>,
                response: Response<List<DataCartResponseItem>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!= null) {
                        _cart.postValue(data)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }
            }
            override fun onFailure(call: Call<List<DataCartResponseItem>>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }
        })
    }

    fun postCartUsers(name: String,
                      userId:Int,
                      product_image:String,
                      price:String,
                      description:String){
        ApiClient.instance.postCartUser(userId, DataCart(name, product_image, price, description))
            .enqueue(object : Callback<List<DataCartResponseItem>>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<List<DataCartResponseItem>>,
                    response: Response<List<DataCartResponseItem>>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        if (data!= null) {
                            _cart.postValue(data)
                        }else{
                            Log.e("Error : ", "onFailure : ${response.message()}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<DataCartResponseItem>>, t: Throwable) {
                    Log.e("Error : ", "onFailure : ${t.message}")
                }
            })
    }
}