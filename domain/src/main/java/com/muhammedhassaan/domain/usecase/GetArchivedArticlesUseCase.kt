package com.muhammedhassaan.domain.usecase

import com.muhammedhassaan.domain.model.Article
import com.muhammedhassaan.domain.repo.Repository
import com.muhammedhassaan.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetArchivedArticlesUseCase(
    private val repository: Repository
){
    operator fun invoke(): Flow<ResultState<List<Article>>> = flow {
        try {
            emit(ResultState.Loading())
            val cachedNews = repository.getArchivedArticles()
            emit(ResultState.Success(cachedNews))
        }catch (e: IOException){
            emit(ResultState.Error(message = e.message?: "An Unexpected Error Occurred"))
        }
    }
}