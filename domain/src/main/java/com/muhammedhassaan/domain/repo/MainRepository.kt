package com.muhammedhassaan.domain.repo

import com.muhammedhassaan.domain.model.Article

interface MainRepository {

    suspend fun getNewsFromRemoteAndSaveToLocal()

    suspend fun getNewsFromLocal(): List<Article>
}