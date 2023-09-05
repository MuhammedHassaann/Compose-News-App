package com.muhammedhassaan.yaganews.ui.screens.archived

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.muhammedhassaan.yaganews.R
import com.muhammedhassaan.yaganews.ui.screens.home.NewsListItem

@Composable
fun ArchivedScreen(
    viewModel: ArchivedViewModel = hiltViewModel(),
    onNavigateClick: (Int)->Unit
){

    val articlesState = viewModel.newsListState.value
    Box(
        Modifier.fillMaxSize()
    ){
        LazyColumn(){
            items(articlesState.articles){article ->
                NewsListItem(
                    article = article,
                    onItemClick = {
                        onNavigateClick(article.id)
                    },
                    onArchiveClick = {
                        viewModel.deleteArticleFromArchived(article.id,!article.isArchived)
                    }
                )
            }
        }
        if (articlesState.isLoading){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        if (articlesState.error.isNotBlank()){
            Text(
                text = articlesState.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (articlesState.articles.isEmpty()){
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "You Have No Archived Articles",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.article),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
        }
    }
}