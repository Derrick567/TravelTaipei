package com.example.traveltaipei.home.model.remote.dto

import com.example.traveltaipei.home.model.News
import com.google.gson.annotations.SerializedName

data class NewsResult(
    @SerializedName("data")
    val news: List<News>,
    val total: Int
)