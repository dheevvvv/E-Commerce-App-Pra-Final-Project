package com.pucuk.e_commerce_app_pra_final_project.model.category_product_response


import com.google.gson.annotations.SerializedName

data class DataCategoryProductResponseItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)