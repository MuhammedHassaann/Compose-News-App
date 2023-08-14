package com.muhammedhassaan.data.repo.remote

import com.muhammedhassaan.data.remote.ApiService
import com.muhammedhassaan.domain.model.NewsResponse
import com.muhammedhassaan.domain.repo.remote.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(
    private val apiService: ApiService
): RemoteRepository {
    override suspend fun getAllNews(): NewsResponse = apiService.getNews()

}