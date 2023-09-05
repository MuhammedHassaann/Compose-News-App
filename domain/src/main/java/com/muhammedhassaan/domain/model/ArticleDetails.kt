package com.muhammedhassaan.domain.model

data class ArticleDetails(
    val id: Int,
    val title: String,
    val content: String?,
    val url: String,
    val imgUrl: String?,
    val isArchived: Boolean
)
