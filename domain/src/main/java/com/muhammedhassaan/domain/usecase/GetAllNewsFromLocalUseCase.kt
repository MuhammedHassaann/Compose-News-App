package com.muhammedhassaan.domain.usecase

import com.muhammedhassaan.domain.repo.MainRepository

class GetAllNewsFromLocalUseCase(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke() = mainRepository.getNewsFromLocal()
}