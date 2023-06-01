package com.pucuk.e_commerce_app_pra_final_project.datastore_prefs

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject




class UserManager @Inject constructor(private val context: Context) {
    private val preferenceName = "prefs"
    private val Context.datastore by preferencesDataStore(preferenceName)

    private val USER_ID = intPreferencesKey("userId")
    private val IS_LOGIN_KEY = booleanPreferencesKey("is_login")

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var instance: UserManager? = null

        fun getInstance(context: Context): UserManager {
            return instance ?: synchronized(this) {
                instance ?: UserManager(context).also { instance = it }
            }
        }
    }

    suspend fun saveData ( user_id:Int, is_login_key:Boolean){
        context.datastore.edit {
            it [USER_ID] = user_id
            it [IS_LOGIN_KEY] = is_login_key
        }
    }

    suspend fun clearData(){
        context.datastore.edit {
            it.clear()
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return context.datastore.data
            .map { preferences ->
                preferences[IS_LOGIN_KEY] ?: false
            }
    }

    suspend fun getUserId():Int{
        val preferences = context.datastore.data.first()
        return preferences[USER_ID] ?: 1
    }





}