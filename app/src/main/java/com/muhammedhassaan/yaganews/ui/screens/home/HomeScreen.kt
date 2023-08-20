package com.muhammedhassaan.yaganews.ui.screens.home



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.muhammedhassaan.domain.model.Article
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun HomeScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    onNavigateClick: (Int)->Unit
){

    LaunchedEffect(true){
        viewModel.resetList()
        viewModel.refreshNewsScreen()
    }

    val articlesState = viewModel.newsListState.value

    Box (
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(){
            items(articlesState.articles){article ->
                NewsListItem(
                    article = article,
                    onItemClick = {
                        onNavigateClick(article.id)
                    },
                    onArchiveClick = {
                        viewModel.updateArticleArchivedStatus(article.id, !article.isArchived)
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
    }

}

@Composable
fun NewsListItem(
    article: Article,
    onItemClick: ()-> Unit,
    onArchiveClick: ()-> Unit
){



    Card (
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp)
            .clickable { onItemClick() }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            article.imgUrl?.let { CoilImage(imageUrl = it, Modifier.weight(0.3f)) }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(0.6f),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = article.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = article.publishedAt,
                    fontSize = 14.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Light
                )
/*                article.description?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }*/
            }
            var isArchivedState by remember { mutableStateOf(article.isArchived) }
            IconButton(

                onClick = {
                    isArchivedState = !isArchivedState
                    onArchiveClick() },
                modifier = Modifier.weight(0.1f)
            ) {
                val icon = if(isArchivedState) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                Icon(imageVector = icon, contentDescription = "archive news")
            }
        }
    }
}


@Composable
fun CoilImage(
    imageUrl: String,
    modifier: Modifier
){
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(300)
                .transformations(
                    RoundedCornersTransformation(50f)
                )
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        ){
            if (painter.state is AsyncImagePainter.State.Loading){
                CircularProgressIndicator()
            }else{
                SubcomposeAsyncImageContent()
            }
        }
    }
}



