package com.example.traveltaipei.home.model.remote

import com.example.traveltaipei.home.model.remote.dto.AttractionsResult
import com.example.traveltaipei.home.model.remote.dto.NewsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface TravelAPIService {

    @Headers("accept: application/json")
    @GET("{lang}/Attractions/All?page=1")
    fun getAttractions(@Path("lang") lang: String): Call<AttractionsResult>


    @Headers("accept: application/json")
    @GET("{lang}/Events/News?page=1")
    fun getNews(@Path("lang") lang: String): Call<NewsResult>
}