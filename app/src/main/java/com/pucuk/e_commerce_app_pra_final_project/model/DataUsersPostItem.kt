package com.example.shoopeeapplication.model

import com.google.gson.annotations.SerializedName

data class DataUsersPostItem(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("password")
    val password: String? = null
)