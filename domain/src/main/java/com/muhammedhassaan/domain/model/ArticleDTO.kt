package com.muhammedhassaan.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleDTO(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

fun List<ArticleDTO>.asArticle(): List<Article>{
    return map{
        Article(
            author = it.author,
            content = it.content,
            description = it.description,
            publishedAt = it.publishedAt,
            source = it.source,
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage
        )
    }
}
