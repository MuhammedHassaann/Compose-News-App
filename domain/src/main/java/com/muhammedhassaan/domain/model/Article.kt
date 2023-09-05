package com.muhammedhassaan.domain.model

data class Article(
    val id: Int,
    val title: String,
    val publishedAt: String,
    val description: String?,
    val imgUrl: String?,
    var isArchived: Boolean
)
