package com.muhammedhassaan.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.muhammedhassaan.data.local.dto.ArticleLocalDTO

data class ArticleRemoteDTO(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
)

data class NewsResponse(
    val articles: List<ArticleRemoteDTO>,
    val status: String,
    val totalResults: Int
)

data class Source(
    @SerializedName("id")
    val sourceId: String?,
    @SerializedName("name")
    val sourceName: String?
){
    constructor(): this(null, null)
}

fun ArticleRemoteDTO.asArticleLocalDTO(): ArticleLocalDTO{
    return ArticleLocalDTO(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source,
            title = title,
            url = url,
            urlToImage = urlToImage
    )
}