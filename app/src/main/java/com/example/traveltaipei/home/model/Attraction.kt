package com.example.traveltaipei.home.model

import com.example.traveltaipei.home.data.remote.dto.Category
import com.example.traveltaipei.home.data.remote.dto.Image
import com.example.traveltaipei.home.data.remote.dto.LinkX
import com.example.traveltaipei.home.data.remote.dto.Service

data class Attraction(
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val introduction: String= "",
    val category: List<Category> = emptyList(),
    val distric: String = "",
    val elong: Double = 0.0,
    val email: String= "",
    val facebook: String= "",
    val fax: String= "",
    val files: List<Any> = emptyList(),
    val friendly: List<Any> = emptyList(),
    val images: List<Image> = emptyList(),
    val links: List<LinkX> = emptyList(),
    val modified: String= "",
    val months: String = "",

    val name_zh: Any = "",
    val nlat: Double = 0.0,
    val official_site: String = "",
    val open_status: Int = 0,
    val open_time: String= "",
    val remind: String= "",
    val service: List<Service> = emptyList(),
    val staytime: String= "",
    //val target: List<Target>,
    val tel: String= "",
    val ticket: String= "",
    val url: String= "",
    val zipcode: String = ""
)