package com.pucuk.e_commerce_app_pra_final_project.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataNewsResponseItem(
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id_news")
    val idNews: String,
    @SerializedName("news_image")
    val newsImage: String,
    @SerializedName("title")
    val title: String
): Serializable