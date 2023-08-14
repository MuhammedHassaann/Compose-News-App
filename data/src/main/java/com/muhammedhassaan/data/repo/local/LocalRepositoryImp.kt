package com.muhammedhassaan.data.repo.local

import com.muhammedhassaan.data.local.ArticleDao
import com.muhammedhassaan.domain.model.ArticleDTO
import com.muhammedhassaan.domain.repo.local.LocalRepository
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(
    private val articleDao: ArticleDao
): LocalRepository {
    override suspend fun insertArticles(articles: List<ArticleDTO>) {
        articleDao.insertArticles(articles)
    }

    override suspend fun getAllArticles(): List<ArticleDTO> {
        return articleDao.getAllArticles()
    }

}