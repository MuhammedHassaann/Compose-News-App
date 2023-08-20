package com.muhammedhassaan.yaganews.di

import com.muhammedhassaan.domain.repo.Repository
import com.muhammedhassaan.domain.usecase.GetArchivedArticlesUseCase
import com.muhammedhassaan.domain.usecase.GetArticleDetailsByIdUseCase
import com.muhammedhassaan.domain.usecase.GetCachedNewsUseCase
import com.muhammedhassaan.domain.usecase.RefreshNewsUseCase
import com.muhammedhassaan.domain.usecase.UpdateArticleArchivedStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetArchivedArticlesUseCase(repository: Repository): GetArchivedArticlesUseCase{
        return GetArchivedArticlesUseCase(repository)
    }

    @Provides
    fun provideGetArticleDetailsByIdUseCase(repository: Repository): GetArticleDetailsByIdUseCase{
        return GetArticleDetailsByIdUseCase(repository)
    }

    @Provides
    fun provideGetCachedNewsUseCase(repository: Repository): GetCachedNewsUseCase{
        return GetCachedNewsUseCase(repository)
    }

    @Provides
    fun provideRefreshNewsUseCase(repository: Repository): RefreshNewsUseCase{
        return RefreshNewsUseCase(repository)
    }

    @Provides
    fun provideUpdateArticleArchivedStatusUseCase(repository: Repository): UpdateArticleArchivedStatusUseCase{
        return UpdateArticleArchivedStatusUseCase(repository)
    }
}