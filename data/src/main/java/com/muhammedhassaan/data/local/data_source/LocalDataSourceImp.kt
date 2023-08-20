package com.muhammedhassaan.data.local.data_source

import com.muhammedhassaan.data.local.ArticleDao
import com.muhammedhassaan.data.local.dto.ArticleLocalDTO
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val articleDao: ArticleDao
): LocalDataSource {
    override suspend fun insertArticles(articles: List<ArticleLocalDTO>) {
        articleDao.insertArticles(articles)
    }

    override suspend fun getCachedNews(): List<ArticleLocalDTO> {
        return articleDao.getAllArticles()
    }

    override suspend fun updateArchivedStatus(articleId: Int, isArchived: Boolean) {
        articleDao.updateArticleArchivedStatus(articleId, isArchived)
    }

    override suspend fun getArticleDetailsById(articleId: Int): ArticleLocalDTO {
        return articleDao.getArticleDetailsById(articleId)
    }

    override suspend fun getArchivedArticles(): List<ArticleLocalDTO> {
        return articleDao.getArchivedArticles()
    }

}