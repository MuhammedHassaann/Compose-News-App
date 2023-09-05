package com.muhammedhassaan.yaganews.ui.screens.home

import com.muhammedhassaan.domain.model.Article

data class ArticleListState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String = ""
)
