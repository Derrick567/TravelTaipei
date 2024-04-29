package com.example.traveltaipei.attraction.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.traveltaipei.home.model.Attraction

class AttractionViewModel : ViewModel() {
    var attraction by mutableStateOf(Attraction())

}