package com.muhammedhassaan.data.remote

import com.muhammedhassaan.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "android"
    ): NewsResponse

}