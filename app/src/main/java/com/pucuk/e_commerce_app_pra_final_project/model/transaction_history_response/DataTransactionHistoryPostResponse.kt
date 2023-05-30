package com.pucuk.e_commerce_app_pra_final_project.model.transaction_history_response


import com.google.gson.annotations.SerializedName

data class DataTransactionHistoryPostResponse(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_trans")
    val idTrans: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("userId")
    val userId: String
)