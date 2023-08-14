package com.muhammedhassaan.yaganews.di

import com.muhammedhassaan.data.local.ArticleDao
import com.muhammedhassaan.data.remote.ApiService
import com.muhammedhassaan.data.repo.MainRepositoryImp
import com.muhammedhassaan.data.repo.local.LocalRepositoryImp
import com.muhammedhassaan.data.repo.remote.RemoteRepositoryImp
import com.muhammedhassaan.domain.model.ArticleDTO
import com.muhammedhassaan.domain.repo.MainRepository
import com.muhammedhassaan.domain.repo.local.LocalRepository
import com.muhammedhassaan.domain.repo.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRemoteRepository(apiService: ApiService): RemoteRepository{
        return RemoteRepositoryImp(apiService)
    }

    @Provides
    fun provideLocalRepository(articleDao: ArticleDao): LocalRepository{
        return LocalRepositoryImp(articleDao)
    }

    @Provides
    fun provideMainRepository(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): MainRepository{
        return MainRepositoryImp(remoteRepository, localRepository)
    }
}