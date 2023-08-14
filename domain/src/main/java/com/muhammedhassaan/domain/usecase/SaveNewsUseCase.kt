package com.muhammedhassaan.domain.usecase

import com.muhammedhassaan.domain.repo.MainRepository

class SaveNewsUseCase(
    private val mainRepository: MainRepository,
) {

    suspend operator fun invoke() =
        mainRepository.getNewsFromRemoteAndSaveToLocal()
}