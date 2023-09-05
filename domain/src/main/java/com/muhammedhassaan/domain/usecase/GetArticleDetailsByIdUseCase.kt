package com.muhammedhassaan.domain.usecase

import com.muhammedhassaan.domain.model.ArticleDetails
import com.muhammedhassaan.domain.repo.Repository
import com.muhammedhassaan.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetArticleDetailsByIdUseCase(
    private val repository: Repository
){
    operator fun invoke(
        articleId: Int
    ): Flow<ResultState<ArticleDetails>> = flow {
        try {
            emit(ResultState.Loading())
            val articleDetails = repository.getArticleDetailsById(articleId)
            emit(ResultState.Success(articleDetails))
        }catch (e: IOException){
            emit(ResultState.Error(message = e.message?: "An Unexpected Error Occurred"))
        }
    }
}