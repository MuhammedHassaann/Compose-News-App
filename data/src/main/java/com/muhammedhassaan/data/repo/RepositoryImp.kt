package com.muhammedhassaan.data.repo

import com.muhammedhassaan.data.local.dto.asArticle
import com.muhammedhassaan.domain.repo.Repository
import com.muhammedhassaan.data.local.data_source.LocalDataSource
import com.muhammedhassaan.data.local.dto.asArticleDetails
import com.muhammedhassaan.data.remote.data_source.RemoteDataSource
import com.muhammedhassaan.domain.model.Article
import com.muhammedhassaan.domain.model.ArticleDetails
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): Repository {

    override suspend fun cacheNews() {
        val remoteNewsList = remoteDataSource.getAllNews()
        val archivedIdsList = localDataSource.getArchivedArticles().map { it.id }

        val news = remoteNewsList.map { remoteNews->
            val isArchived = remoteNews.id in archivedIdsList

            remoteNews.apply {
                this.isArchived = isArchived
            }
        }

        localDataSource.insertArticles(news)
    }

    override suspend fun getCachedNews(): List<Article> {
        return localDataSource.getCachedNews().map { it.asArticle() }
    }

    override suspend fun refreshNews(): List<Article> {
        cacheNews()
        return getCachedNews()
    }

    override suspend fun updateArticleArchivedStatus(articleId: Int, isArchived: Boolean) {
        localDataSource.updateArchivedStatus(articleId, isArchived)
    }

    override suspend fun getArticleDetailsById(articleId: Int): ArticleDetails {
        return localDataSource.getArticleDetailsById(articleId).asArticleDetails()
    }

    override suspend fun getArchivedArticles(): List<Article> {
        return localDataSource.getArchivedArticles().map { it.asArticle() }
    }

}