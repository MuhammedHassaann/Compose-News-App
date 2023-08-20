package com.muhammedhassaan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammedhassaan.data.local.dto.ArticleLocalDTO

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleLocalDTO>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleLocalDTO>

    @Query("UPDATE articles SET isArchived = :isArchived WHERE id = :articleId")
    suspend fun updateArticleArchivedStatus(articleId: Int, isArchived: Boolean)

    @Query("SELECT * FROM articles WHERE id = :articleId")
    suspend fun getArticleDetailsById(articleId: Int): ArticleLocalDTO

    @Query("SELECT * FROM articles WHERE isArchived = 1")
    suspend fun getArchivedArticles(): List<ArticleLocalDTO>


}