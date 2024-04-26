package com.example.traveltaipei.home.data.remote.dto

data class News(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val files: List<Any> = emptyList(),
    val begin: Any = "",
    val end: Any= "",
    val links: List<Link> = emptyList(),
    val modified: String = "",
    val posted: String = "",
    val url: String = ""
)