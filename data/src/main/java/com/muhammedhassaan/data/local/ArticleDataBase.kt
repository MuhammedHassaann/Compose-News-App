package com.muhammedhassaan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muhammedhassaan.domain.model.ArticleDTO

@Database(entities = [ArticleDTO::class], version = 1, exportSchema = false)
abstract class ArticleDataBase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}