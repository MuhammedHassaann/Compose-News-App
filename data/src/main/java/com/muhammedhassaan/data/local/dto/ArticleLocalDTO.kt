package com.muhammedhassaan.data.local.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muhammedhassaan.data.remote.dto.Source
import com.muhammedhassaan.domain.model.Article
import com.muhammedhassaan.domain.model.ArticleDetails

@Entity(tableName = "articles")
data class ArticleLocalDTO(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    @Embedded val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?,
    var isArchived: Boolean = false
)

fun ArticleLocalDTO.asArticle(): Article{
    return Article(
        id = id,
        title = title,
        publishedAt = publishedAt,
        description = description,
        imgUrl = urlToImage,
        isArchived = isArchived
    )
}

fun ArticleLocalDTO.asArticleDetails(): ArticleDetails{
    return ArticleDetails(
        id = id,
        title = title,
        content = content,
        url =  url,
        imgUrl = urlToImage,
        isArchived = isArchived
    )
}
