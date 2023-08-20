package com.muhammedhassaan.data.remote.data_source

import com.muhammedhassaan.data.local.dto.ArticleLocalDTO
import com.muhammedhassaan.data.remote.ApiService
import com.muhammedhassaan.data.remote.dto.asArticleLocalDTO
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService
): RemoteDataSource {
    override suspend fun getAllNews(): List<ArticleLocalDTO> {
        val remoteNews = apiService.getNews()
        return if (remoteNews.status == "ok"){
            remoteNews.articles.map { it.asArticleLocalDTO() }
        }else emptyList()
    }
}