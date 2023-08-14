package com.muhammedhassaan.domain.repo.local

import com.muhammedhassaan.domain.model.ArticleDTO

interface LocalRepository {
    suspend fun insertArticles(articles: List<ArticleDTO>)

    suspend fun getAllArticles(): List<ArticleDTO>
}