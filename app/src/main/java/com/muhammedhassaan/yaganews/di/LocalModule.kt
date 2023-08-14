package com.muhammedhassaan.yaganews.di

import android.content.Context
import androidx.room.Room
import com.muhammedhassaan.data.local.ArticleDao
import com.muhammedhassaan.data.local.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context): ArticleDataBase{
        return Room.databaseBuilder(
            context = context,
            klass = ArticleDataBase::class.java,
            name = "article_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(articleDataBase: ArticleDataBase): ArticleDao{
        return articleDataBase.articleDao()
    }
}