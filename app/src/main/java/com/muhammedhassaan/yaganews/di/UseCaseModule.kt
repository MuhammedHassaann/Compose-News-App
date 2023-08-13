package com.muhammedhassaan.yaganews.di

import com.muhammedhassaan.domain.repo.remote.RemoteRepository
import com.muhammedhassaan.domain.usecase.GetNewsFromRemoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllNewsUseCase(remoteRepository: RemoteRepository): GetNewsFromRemoteUseCase {
        return GetNewsFromRemoteUseCase(remoteRepository)
    }
}