package com.muhammedhassaan.domain.usecase

import com.muhammedhassaan.domain.repo.Repository
import com.muhammedhassaan.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UpdateArticleArchivedStatusUseCase(
    private val repository: Repository
){
    operator fun invoke(
        articleId: Int,
        isArchived: Boolean
    ): Flow<ResultState<Boolean>> = flow {
        try {
            repository.updateArticleArchivedStatus(articleId, isArchived)
            emit(ResultState.Success(true))
        }catch (e: IOException){
            emit(ResultState.Error(message = e.message ?: "An Unexpected Error Occurred"))
        }
    }
}