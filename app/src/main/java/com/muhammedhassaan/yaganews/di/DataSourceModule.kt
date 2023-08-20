package com.muhammedhassaan.yaganews.di

import com.muhammedhassaan.data.local.ArticleDao
import com.muhammedhassaan.data.remote.ApiService
import com.muhammedhassaan.data.local.data_source.LocalDataSourceImp
import com.muhammedhassaan.data.remote.data_source.RemoteDataSourceImp
import com.muhammedhassaan.data.local.data_source.LocalDataSource
import com.muhammedhassaan.data.remote.data_source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImp(apiService)
    }

    @Provides
    fun provideLocalDataSource(articleDao: ArticleDao): LocalDataSource {
        return LocalDataSourceImp(articleDao)
    }

}