package com.pucuk.e_commerce_app_pra_final_project.model.transaction_history_response

data class DataTransactionHistory(
    val name:String,
    val picture:String,
    val price:Number,
    val description:String,
    val amount: Number,
    val total:Number
)
