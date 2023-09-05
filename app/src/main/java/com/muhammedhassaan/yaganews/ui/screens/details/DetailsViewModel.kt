package com.muhammedhassaan.yaganews.ui.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedhassaan.domain.usecase.GetArticleDetailsByIdUseCase
import com.muhammedhassaan.domain.usecase.UpdateArticleArchivedStatusUseCase
import com.muhammedhassaan.domain.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getArticleDetailsByIdUseCase: GetArticleDetailsByIdUseCase,
    private val updateArticleArchivedStatusUseCase: UpdateArticleArchivedStatusUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _newsListState = mutableStateOf(ArticleDetailsState())
    val newsListState: State<ArticleDetailsState> = _newsListState

    init {
        savedStateHandle.get<Int>("id")?.let {articleId ->
            getArticlesDetailsById(articleId)
        }
    }


    private fun getArticlesDetailsById(articleId: Int){
        getArticleDetailsByIdUseCase(articleId).onEach {resultState ->
            when(resultState){
                is ResultState.Success -> {
                    _newsListState.value =
                        ArticleDetailsState(article = resultState.data)
                }
                is ResultState.Loading -> {
                    _newsListState.value = ArticleDetailsState(isLoading = true)
                }
                is ResultState.Error -> {
                    _newsListState.value = ArticleDetailsState(
                        error = resultState.message ?: "An Unexpected Error Occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateArticleArchivedStatus(articleId: Int, isArchived: Boolean) {
        updateArticleArchivedStatusUseCase(articleId, isArchived).onEach {resultState ->
            if (resultState is ResultState.Success){

            }
        }.launchIn(viewModelScope)
    }
}