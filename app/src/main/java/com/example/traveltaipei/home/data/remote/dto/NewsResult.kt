package com.example.traveltaipei.home.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsResult(
    @SerializedName("data")
    val news: List<News>,
    val total: Int
)