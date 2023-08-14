package com.muhammedhassaan.data.repo

import com.muhammedhassaan.domain.model.Article
import com.muhammedhassaan.domain.model.asArticle
import com.muhammedhassaan.domain.model.asArticleDTO
import com.muhammedhassaan.domain.repo.MainRepository
import com.muhammedhassaan.domain.repo.local.LocalRepository
import com.muhammedhassaan.domain.repo.remote.RemoteRepository
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
): MainRepository {

    override suspend fun getNewsFromRemoteAndSaveToLocal() {
        val response = remoteRepository.getAllNews()
        if (response.status == "ok") {
            localRepository.insertArticles(
                response.articles.asArticleDTO()
            )
        }
    }

    override suspend fun getNewsFromLocal(): List<Article> {
        return localRepository.getAllArticles().asArticle()
    }

}