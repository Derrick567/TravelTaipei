package com.example.traveltaipei.home.presentation

import com.example.traveltaipei.home.data.remote.dto.News
import com.example.traveltaipei.home.model.Attraction


data class HomeUiState(
    val attractions: List<Attraction> = emptyList(),
    val newsList: List<News> = emptyList()
)
