package com.muhammedhassaan.yaganews.ui.screens.home

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedhassaan.domain.usecase.GetCachedNewsUseCase
import com.muhammedhassaan.domain.usecase.RefreshNewsUseCase
import com.muhammedhassaan.domain.usecase.UpdateArticleArchivedStatusUseCase
import com.muhammedhassaan.domain.utils.ResultState
import com.muhammedhassaan.yaganews.utils.Internet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getCachedNewsUseCase: GetCachedNewsUseCase,
    private val refreshNewsUseCase: RefreshNewsUseCase,
    private val updateArticleArchivedStatusUseCase: UpdateArticleArchivedStatusUseCase,
    private val application: Application
) : ViewModel() {

    private val _newsListState = mutableStateOf(ArticleListState())
    val newsListState: State<ArticleListState> = _newsListState


    init {
        refreshNewsScreen()
    }

    fun resetList(){
        _newsListState.value = ArticleListState()
    }

    private fun getCachedNews() {
        getCachedNewsUseCase().onEach { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    _newsListState.value =
                        ArticleListState(articles = resultState.data ?: emptyList())
                }
                is ResultState.Loading -> {
                    _newsListState.value = ArticleListState(isLoading = true)
                }

                is ResultState.Error -> {
                    _newsListState.value = ArticleListState(
                        error = resultState.message ?: "An Unexpected Error Occurred"
                    )
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun refreshNews() {
        if (Internet.isInternetConnected(application.applicationContext)) {
            refreshNewsUseCase().onEach { resultState ->
                when (resultState) {
                    is ResultState.Success -> {
                        _newsListState.value =
                            ArticleListState(articles = resultState.data ?: emptyList())
                    }
                    is ResultState.Loading -> {
                        _newsListState.value = ArticleListState(isLoading = true)
                    }

                    is ResultState.Error -> {
                        _newsListState.value = ArticleListState(
                            error = resultState.message ?: "An Unexpected Error Occurred"
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun refreshNewsScreen(){
        getCachedNews()
        refreshNews()
    }

    fun updateArticleArchivedStatus(articleId: Int, isArchived: Boolean) {
        updateArticleArchivedStatusUseCase(articleId, isArchived).launchIn(viewModelScope)
    }
}