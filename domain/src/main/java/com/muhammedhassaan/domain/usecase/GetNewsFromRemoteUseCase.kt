package com.muhammedhassaan.domain.usecase

import com.muhammedhassaan.domain.repo.remote.RemoteRepository

class GetNewsFromRemoteUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke() = remoteRepository.getAllNews()

}