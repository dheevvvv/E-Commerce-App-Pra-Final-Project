package com.pucuk.e_commerce_app_pra_final_project.network

import android.app.Application
import com.pucuk.e_commerce_app_pra_final_project.datastore_prefs.UserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    private const val  BASE_URL ="https://646b1d797d3c1cae4ce33622.mockapi.io/"

    @Singleton
    @get:Provides
    val instance : ApiService by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserManager(application: Application): UserManager {
        return UserManager.getInstance(application)
    }
}