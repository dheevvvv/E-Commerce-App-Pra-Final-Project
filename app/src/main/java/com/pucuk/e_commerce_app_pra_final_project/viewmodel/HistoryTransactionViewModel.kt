package com.pucuk.e_commerce_app_pra_final_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pucuk.e_commerce_app_pra_final_project.model.cart_response.DataCartResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.transaction_history_response.DataHistoryTransactionResponseItem
import com.pucuk.e_commerce_app_pra_final_project.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HistoryTransactionViewModel:ViewModel()  {
    private val _historyTrans: MutableLiveData<List<DataHistoryTransactionResponseItem>> = MutableLiveData()
    val historyTrans : LiveData<List<DataHistoryTransactionResponseItem>> get() = _historyTrans

    fun callApiHistoryTrans(userId:Int){
        ApiClient.instance.getAllHistoryTransactionUserById(userId)
            .enqueue(object : Callback<List<DataHistoryTransactionResponseItem>>{
                override fun onResponse(
                    call: Call<List<DataHistoryTransactionResponseItem>>,
                    response: Response<List<DataHistoryTransactionResponseItem>>
                ) {
                    if (response.isSuccessful){
                        val data =  response.body()
                        if (data!= null){
                            _historyTrans.postValue(data!!)
                        }else{
                            Log.e("Error : ", "onFailure : ${response.message()}")
                        }
                    }
                }

                override fun onFailure(
                    call: Call<List<DataHistoryTransactionResponseItem>>,
                    t: Throwable
                ) {
                    Log.e("Error : ", "onFailure : ${t.message}")
                }

            })
    }
}