package com.example.traveltaipei.home.model.remote.dto

import com.example.traveltaipei.home.model.Attraction
import com.google.gson.annotations.SerializedName

data class AttractionsResult(
    @SerializedName("data")
    val attractions: List<Attraction>,
    val total: Int
)