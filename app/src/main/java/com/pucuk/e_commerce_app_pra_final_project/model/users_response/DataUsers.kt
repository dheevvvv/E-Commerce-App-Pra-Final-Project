package com.pucuk.e_commerce_app_pra_final_project.model.users_response

import com.google.gson.annotations.SerializedName

data class DataUsers(
    @SerializedName("email")
    val email: String,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)