package com.muhammedhassaan.yaganews.di

import com.muhammedhassaan.domain.repo.MainRepository
import com.muhammedhassaan.domain.repo.local.LocalRepository
import com.muhammedhassaan.domain.usecase.GetAllNewsFromLocalUseCase
import com.muhammedhassaan.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSaveNewsUseCase(mainRepository: MainRepository): SaveNewsUseCase {
        return SaveNewsUseCase(mainRepository)
    }

    @Provides
    fun provideGetAllNewsFromLocalUseCase(mainRepository: MainRepository): GetAllNewsFromLocalUseCase {
        return GetAllNewsFromLocalUseCase(mainRepository)
    }

}