package com.example.traveltaipei.home.data.remote.dto

import com.example.traveltaipei.home.model.Attraction
import com.google.gson.annotations.SerializedName

data class AttractionsResult(
    @SerializedName("data")
    val attractions: List<Attraction>,
    val total: Int
)