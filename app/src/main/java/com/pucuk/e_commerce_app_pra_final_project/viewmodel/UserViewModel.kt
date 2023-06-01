package com.pucuk.e_commerce_app_pra_final_project.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pucuk.e_commerce_app_pra_final_project.datastore_prefs.UserManager
import com.pucuk.e_commerce_app_pra_final_project.model.users_response.DataUsers
import com.pucuk.e_commerce_app_pra_final_project.model.users_response.DataUsersPostItem
import com.pucuk.e_commerce_app_pra_final_project.model.users_response.DataUsersResponseItem
import com.pucuk.e_commerce_app_pra_final_project.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val Client: ApiService, val userManager: UserManager ): ViewModel() {

    private var livedataUser : MutableLiveData<List<DataUsersPostItem>> = MutableLiveData()
    val dataPostUser: LiveData<List<DataUsersPostItem>> get() = livedataUser

    private val _dataLoginUser: MutableLiveData<DataUsersResponseItem?> = MutableLiveData()
    val dataLoginUser: LiveData<DataUsersResponseItem?> get() = _dataLoginUser

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> = _userId

    fun loginUser(email: String, password: String) {
        Client.getAllUser().enqueue(object : Callback<List<DataUsersResponseItem>> {
            override fun onResponse(
                call: Call<List<DataUsersResponseItem>>,
                response: Response<List<DataUsersResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()
                    val loginUser = users?.find { it.email == email && it.password == password }
                    _dataLoginUser.value = loginUser
                } else {
                    _dataLoginUser.value = null
                }
            }

            override fun onFailure(call: Call<List<DataUsersResponseItem>>, t: Throwable) {
                _dataLoginUser.value = null
            }
        })
    }
    fun postUserRegister(email: String, name: String, password: String){

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
    fun getUserId() {
        viewModelScope.launch {
            val userId = userManager.getUserId()
            _userId.postValue(userId)
        }
    }
}