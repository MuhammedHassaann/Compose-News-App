package com.muhammedhassaan.data.local.data_source


import com.muhammedhassaan.data.local.dto.ArticleLocalDTO

interface LocalDataSource {
    suspend fun insertArticles(articles: List<ArticleLocalDTO>)

    suspend fun getCachedNews(): List<ArticleLocalDTO>

    suspend fun updateArchivedStatus(articleId: Int, isArchived: Boolean)

    suspend fun getArticleDetailsById(articleId: Int): ArticleLocalDTO

    suspend fun getArchivedArticles(): List<ArticleLocalDTO>
}