package com.example.traveltaipei.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ApiClient
import com.example.traveltaipei.home.data.remote.dto.AttractionsResult
import com.example.traveltaipei.home.data.remote.dto.NewsResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    val TAG = "MainViewModel--"
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    init {
        getAttractions()
        getNews()
    }


    private fun getNews() {
        val call2 = ApiClient.apiService.getNews("zh-tw")
        call2.enqueue(object : Callback<NewsResult> {
            override fun onResponse(call: Call<NewsResult>, response: Response<NewsResult>) {
                if (response.isSuccessful) {
                    val newsResult = response.body()
                    if (newsResult != null) {
                        Log.d(TAG, "size = " + newsResult.news.size)
                        _uiState.update {  currentState ->
                            newsResult.news.take(3).let {
                                currentState.copy(
                                    newsList = it
                                )
                            }
                        }
                    }
                } else {
                    Log.d(TAG, "getNews error : ${response.body().toString()}")
                }
            }

            override fun onFailure(call: Call<NewsResult>, t: Throwable) {
                Log.d(TAG, t.message.toString())
            }

        })
    }

    private fun getAttractions() {
        val call = ApiClient.apiService.getAttractions("zh-tw")
        call.enqueue(object : Callback<AttractionsResult> {
            override fun onResponse(
                call: Call<AttractionsResult>,
                response: Response<AttractionsResult>
            ) {
                if (response.isSuccessful) {
                    val attractionsResult = response.body()
                    if (attractionsResult != null) {
                        _uiState.update {  currentState ->
                            attractionsResult.attractions.take(6).let {
                                currentState.copy(
                                    attractions = it
                                )
                            }
                        }
                    }


                    Log.d(TAG, "total = ${attractionsResult?.attractions?.size}")
                } else {
                    Log.d(TAG, "error : ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<AttractionsResult>, t: Throwable) {
                Log.d(TAG, t.message.toString())
            }
        })
    }
}