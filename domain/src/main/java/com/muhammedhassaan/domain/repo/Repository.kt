package com.muhammedhassaan.domain.repo

import com.muhammedhassaan.domain.model.Article
import com.muhammedhassaan.domain.model.ArticleDetails


interface Repository {

    suspend fun cacheNews()

    suspend fun getCachedNews(): List<Article>

    suspend fun refreshNews(): List<Article>

    suspend fun updateArticleArchivedStatus(articleId : Int, isArchived: Boolean)

    suspend fun getArticleDetailsById(articleId: Int): ArticleDetails

    suspend fun getArchivedArticles(): List<Article>
}