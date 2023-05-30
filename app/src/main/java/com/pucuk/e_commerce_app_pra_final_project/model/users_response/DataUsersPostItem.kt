package com.pucuk.e_commerce_app_pra_final_project.model.users_response

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