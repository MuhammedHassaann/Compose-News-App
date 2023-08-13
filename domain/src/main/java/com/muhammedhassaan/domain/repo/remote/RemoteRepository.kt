package com.muhammedhassaan.domain.repo.remote

import com.muhammedhassaan.domain.model.NewsResponse

interface RemoteRepository {

    suspend fun getAllNews(): NewsResponse
}