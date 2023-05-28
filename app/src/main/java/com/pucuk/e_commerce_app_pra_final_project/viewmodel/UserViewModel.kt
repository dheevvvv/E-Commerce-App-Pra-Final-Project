package com.example.shoopeeapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoopeeapplication.Network.ApiService
import com.example.shoopeeapplication.model.DataUsers
import com.example.shoopeeapplication.model.DataUsersPostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val Client: ApiService): ViewModel() {

    private var livedataUser : MutableLiveData<List<DataUsersPostItem>> = MutableLiveData()
    val dataPostUser: LiveData<List<DataUsersPostItem>> get() = livedataUser
    fun postUserRegister(email: String, name: String, password: String){
        //memakai callback yang retrofit
        Client.registerUser(DataUsers(email, "",name,password)).enqueue(object : Callback<List<DataUsersPostItem>> {
            override fun onResponse(
                call: Call<List<DataUsersPostItem>>,
                response: Response<List<DataUsersPostItem>>

            ) {
                if (response.isSuccessful){
                    livedataUser.postValue(response.body())
                }else{
                    livedataUser.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<DataUsersPostItem>>, t: Throwable) {
                livedataUser.postValue(emptyList())
            }
        })
    }
}