package com.pucuk.e_commerce_app_pra_final_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pucuk.e_commerce_app_pra_final_project.model.category_product_response.DataCategoryProductResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.product_response.DataProductsResponseItem
import com.pucuk.e_commerce_app_pra_final_project.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor (val client: ApiService) : ViewModel(){

    private val _product: MutableLiveData<List<DataProductsResponseItem>> = MutableLiveData()
    val product : LiveData<List<DataProductsResponseItem>> get() = _product


    fun callApiGetAllProductByCategory(id_category:Int){
        client.getAllProductsByCategory(id_category)
            .enqueue(object : Callback<List<DataProductsResponseItem>>{
                override fun onResponse(
                    call: Call<List<DataProductsResponseItem>>,
                    response: Response<List<DataProductsResponseItem>>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _product.postValue(data!!)
                    } else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<DataProductsResponseItem>>, t: Throwable) {
                    Log.e("Error : ", "onFailure : ${t.message}")
                }

            })
    }

//    fun getDetailProduct(id_category: Int,id_product:Int){
//        client.getDetailProduct(id_category,id_product)
//            .enqueue(object : Callback<DataProductsResponseItem>)
//    }
}