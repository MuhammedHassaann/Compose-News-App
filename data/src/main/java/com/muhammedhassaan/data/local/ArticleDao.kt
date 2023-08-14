package com.muhammedhassaan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammedhassaan.domain.model.ArticleDTO

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleDTO>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleDTO>

}