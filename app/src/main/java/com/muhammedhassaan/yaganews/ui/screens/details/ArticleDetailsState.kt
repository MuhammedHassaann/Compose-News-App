package com.muhammedhassaan.yaganews.ui.screens.details

import com.muhammedhassaan.domain.model.ArticleDetails

data class ArticleDetailsState(
    val isLoading: Boolean = false,
    val article: ArticleDetails? = null ,
    val error: String = ""
)
