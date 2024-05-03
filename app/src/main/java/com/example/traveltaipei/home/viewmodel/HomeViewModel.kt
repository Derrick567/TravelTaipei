package com.example.traveltaipei.home.viewmodel

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.example.ApiClient
import com.example.traveltaipei.home.model.remote.dto.AttractionsResult
import com.example.traveltaipei.home.model.News
import com.example.traveltaipei.home.model.remote.dto.NewsResult
import com.example.traveltaipei.home.model.Attraction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel() {

    val TAG = "MainViewModel--"
    private var _news = mutableStateOf<List<News>>(emptyList())
    val news: State<List<News>> = _news

    private var _attractions = mutableStateOf<List<Attraction>>(emptyList())
    val attractions: State<List<Attraction>> = _attractions

    //    private val _uiState = MutableStateFlow(HomeUiState())
//    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    val langs = listOf(
        Pair("正體中文", "zh-tw"),
        Pair("簡體中文", "zh-cn"),
        Pair("英文", "en"),
        Pair("日文", "ja"),
        Pair("韓文", "ko"),
        Pair("西班牙文", "es"),
        Pair("印尼文", "id")
    )
    var currentLanguage by mutableStateOf("zh-tw")
        private set

    init {
        getAttractions()
        getNews()
    }


    fun changeCurrentLanguage(lang: String) {
        currentLanguage = lang
        getAttractions()
        getNews()
        setLocale(lang)
    }

    private fun setLocale(lang: String) {
        val appLocale: LocaleListCompat =
            LocaleListCompat.forLanguageTags(lang)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    private fun getNews() {
        val call2 = ApiClient.apiService.getNews(currentLanguage)
        call2.enqueue(object : Callback<NewsResult> {
            override fun onResponse(call: Call<NewsResult>, response: Response<NewsResult>) {
                if (response.isSuccessful) {
                    val newsResult = response.body()
                    if (newsResult != null) {
                        Log.d(TAG, "size = " + newsResult.news.size)
                        newsResult.news.take(3).let {
                            _news.value = it
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
        val call = ApiClient.apiService.getAttractions(currentLanguage)
        call.enqueue(object : Callback<AttractionsResult> {
            override fun onResponse(
                call: Call<AttractionsResult>,
                response: Response<AttractionsResult>
            ) {
                if (response.isSuccessful) {
                    val attractionsResult = response.body()
                    attractionsResult?.attractions?.take(12)?.let {
                        _attractions.value = it
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