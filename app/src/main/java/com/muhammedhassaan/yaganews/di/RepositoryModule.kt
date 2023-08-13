package com.muhammedhassaan.yaganews.di

import com.muhammedhassaan.data.remote.ApiService
import com.muhammedhassaan.data.repo.remote.RemoteRepositoryImp
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
}