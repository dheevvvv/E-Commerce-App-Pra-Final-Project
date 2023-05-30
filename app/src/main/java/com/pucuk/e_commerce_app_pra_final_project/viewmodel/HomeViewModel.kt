package com.pucuk.e_commerce_app_pra_final_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoopeeapplication.Network.ApiService
import com.pucuk.e_commerce_app_pra_final_project.model.news_response.DataDetailNewsItem
import com.pucuk.e_commerce_app_pra_final_project.model.news_response.DataNewsResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val Client: ApiService): ViewModel() {

    private var livedataNews :MutableLiveData<List<DataNewsResponseItem>> = MutableLiveData()
    val dataNews: LiveData<List<DataNewsResponseItem>> get() = livedataNews
    fun getListNews(){
        Client.getNews().enqueue(object : Callback<List<DataNewsResponseItem>> {
            override fun onResponse(
                call: Call<List<DataNewsResponseItem>>,
                response: Response<List<DataNewsResponseItem>>

            ) {
                if (response.isSuccessful){
                    livedataNews.postValue(response.body())
                }else{
                    livedataNews.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<DataNewsResponseItem>>, t: Throwable) {
                livedataNews.postValue(emptyList())
            }
        })
    }

    private val liveDetailNews: MutableLiveData<DataDetailNewsItem?> = MutableLiveData()
    val detailNews: LiveData<DataDetailNewsItem?> get() = liveDetailNews

    fun getNewsById(id: Int) {
        Client.getDetailNews(id).enqueue(object : Callback<DataDetailNewsItem> {
            override fun onResponse(
                call: Call<DataDetailNewsItem>,
                response: Response<DataDetailNewsItem>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        liveDetailNews.postValue(responseBody)
                    }
                }
            }
            override fun onFailure(call: Call<DataDetailNewsItem>, t: Throwable) {

            }

        })
    }


}