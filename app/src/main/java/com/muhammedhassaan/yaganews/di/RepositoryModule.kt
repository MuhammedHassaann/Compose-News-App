package com.muhammedhassaan.yaganews.di

import com.muhammedhassaan.data.local.data_source.LocalDataSource
import com.muhammedhassaan.data.remote.data_source.RemoteDataSource
import com.muhammedhassaan.data.repo.RepositoryImp
import com.muhammedhassaan.domain.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): Repository {
        return RepositoryImp(remoteDataSource, localDataSource)
    }
}