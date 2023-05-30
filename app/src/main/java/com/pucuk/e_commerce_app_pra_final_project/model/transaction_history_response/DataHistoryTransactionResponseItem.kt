package com.pucuk.e_commerce_app_pra_final_project.model.transaction_history_response


import com.google.gson.annotations.SerializedName

data class DataHistoryTransactionResponseItem(
    @SerializedName("amount")
    val amount: Int,
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
    val price: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("userId")
    val userId: String
)