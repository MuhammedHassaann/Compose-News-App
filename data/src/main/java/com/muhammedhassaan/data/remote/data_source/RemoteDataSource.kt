package com.muhammedhassaan.data.remote.data_source

import com.muhammedhassaan.data.local.dto.ArticleLocalDTO

interface RemoteDataSource {

    suspend fun getAllNews(): List<ArticleLocalDTO>
}