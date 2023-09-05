package com.muhammedhassaan.yaganews

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muhammedhassaan.domain.model.Article
import com.muhammedhassaan.yaganews.ui.screens.archived.ArchivedViewModel
import com.muhammedhassaan.yaganews.ui.screens.home.ArticleListState
import org.junit.Before
import org.junit.Test

class ArchivedViewModelTest {

    private var articleListState = ArticleListState()

    @Before
    fun setUp(){



        articleListState = ArticleListState(
            articles = listOf(
                Article(
                    id = 1,
                    "Title1",
                    publishedAt = "publishedAt1",
                    description = null,
                    imgUrl = null,
                    isArchived = false
                ),
                Article(
                    id = 2,
                    "Title2",
                    publishedAt = "publishedAt2",
                    description = null,
                    imgUrl = null,
                    isArchived = false
                ),
                Article(
                    id = 3,
                    "Title3",
                    publishedAt = "publishedAt3",
                    description = null,
                    imgUrl = null,
                    isArchived = false
                )
            )
        )
    }

    @Test
    fun deleteArticleByIdTest_deleteId2(){
        // GIVEN
        val articleIdToDelete = 2

        // WHEN



    }
}