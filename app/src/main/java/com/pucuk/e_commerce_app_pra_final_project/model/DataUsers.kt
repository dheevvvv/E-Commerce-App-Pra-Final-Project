package com.pucuk.e_commerce_app_pra_final_project.model

import com.google.gson.annotations.SerializedName

data class DataUsers(
    val email: String?,
    val image: String? = "",
    val name: String?,
    val password: String?
)