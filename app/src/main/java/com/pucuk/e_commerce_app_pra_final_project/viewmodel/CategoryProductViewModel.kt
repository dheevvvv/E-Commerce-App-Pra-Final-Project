package com.pucuk.e_commerce_app_pra_final_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pucuk.e_commerce_app_pra_final_project.model.cart_response.DataCartResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.category_product_response.DataCategoryProductResponseItem
import com.pucuk.e_commerce_app_pra_final_project.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryProductViewModel @Inject constructor (val client:ApiService):ViewModel() {
    private val _allCategoryProduct: MutableLiveData<List<DataCategoryProductResponseItem>> = MutableLiveData()
    val allCategoryProduct : LiveData<List<DataCategoryProductResponseItem>> get() = _allCategoryProduct

    private val _categoryProduct: MutableLiveData<DataCategoryProductResponseItem> = MutableLiveData()
    val categoryProduct : LiveData<DataCategoryProductResponseItem> get() = _categoryProduct

    fun callApiGetAllCategoryProduct(){
        client.getAllCategory().enqueue(object : Callback<List<DataCategoryProductResponseItem>>{
            override fun onResponse(
                call: Call<List<DataCategoryProductResponseItem>>,
                response: Response<List<DataCategoryProductResponseItem>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    _allCategoryProduct.postValue(data!!)
                } else{
                    Log.e("Error : ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(
                call: Call<List<DataCategoryProductResponseItem>>,
                t: Throwable
            ) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun getCategoryProduct(){
        client.getCategory().enqueue(object : Callback<DataCategoryProductResponseItem>{
            override fun onResponse(
                call: Call<DataCategoryProductResponseItem>,
                response: Response<DataCategoryProductResponseItem>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    _categoryProduct.postValue(data!!)
                } else{
                    Log.e("Error : ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataCategoryProductResponseItem>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }
}