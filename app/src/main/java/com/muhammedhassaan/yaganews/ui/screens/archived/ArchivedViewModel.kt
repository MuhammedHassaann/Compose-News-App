package com.muhammedhassaan.yaganews.ui.screens.archived

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedhassaan.domain.usecase.GetArchivedArticlesUseCase
import com.muhammedhassaan.domain.usecase.UpdateArticleArchivedStatusUseCase
import com.muhammedhassaan.domain.utils.ResultState
import com.muhammedhassaan.yaganews.ui.screens.home.ArticleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArchivedViewModel @Inject constructor(
    private val getArchivedArticlesUseCase: GetArchivedArticlesUseCase,
    private val updateArticleArchivedStatusUseCase: UpdateArticleArchivedStatusUseCase
): ViewModel() {

    private val _newsListState = mutableStateOf(ArticleListState())
    val newsListState: State<ArticleListState> = _newsListState

    init {
        getCachedNews()
    }

    private fun getCachedNews(){
        getArchivedArticlesUseCase().onEach {resultState ->
            when(resultState){
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

    fun deleteArticleFromArchived(articleId: Int, isArchived: Boolean){
        updateArticleArchivedStatusUseCase(articleId, isArchived).onEach {resultState ->
            if (resultState is ResultState.Success){
                _newsListState.value = deleteArticleById(newsListState.value, articleId)
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteArticleById(articlesState: ArticleListState, articleIdToDelete: Int): ArticleListState {
        val updatedList = articlesState.articles.filter { article -> article.id != articleIdToDelete }
        return ArticleListState(articles = updatedList)
    }


}