package com.muhammedhassaan.yaganews.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.muhammedhassaan.domain.model.Article
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.muhammedhassaan.yaganews.ui.theme.colorCancelEnd
import com.muhammedhassaan.yaganews.ui.theme.colorCancelStart
import com.muhammedhassaan.yaganews.ui.theme.colorEnd
import com.muhammedhassaan.yaganews.ui.theme.colorStart
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(){
    val vm = hiltViewModel<NewsViewModel>()
    var showDeleteDialog = remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (vm.news.value.isEmpty()){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ){
                if (vm.isLoading){
                    GradientButton(
                        text = "Cancel Request",
                        textColor = Color.White,
                        gradient = Brush.horizontalGradient(listOf(colorCancelStart, colorCancelEnd))
                    ) {
                        vm.cancelRequest()
                    }
                }else {
                    GradientButton(
                        text = "Get All News",
                        textColor = Color.White,
                        gradient = Brush.horizontalGradient(listOf(colorStart, colorEnd))
                    ) {
                        if (vm.hasInternet()) {
                            vm.getNews()
                        }else{
                            showDeleteDialog.value = true
                        }
                    }
                }
            }
        }

        if (showDeleteDialog.value){
            MyAlertDialog(
                title = "Error",
                textColor = Color.Black,
                text = "Please Check Your Internet Connection and try again",
                confirmText = "Ok",
                isVisible = showDeleteDialog
            )
        }

        if (vm.isLoading){
            CircularProgressIndicator(modifier = Modifier.padding(top = 300.dp))
        }else {
            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(vm.news.value){article->
                    NewsListItem(article = article)
                }
            }
        }
    }


}

@Composable
fun NewsListItem(
    article: Article
){
    var expanded by rememberSaveable { mutableStateOf(false) }
    val rotateAnim by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = ""
    )

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .clickable {
                expanded = !expanded
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoilImage(article.urlToImage)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (expanded){
                Text(
                    text = article.content,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                )
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Show More",
                    modifier = Modifier
                        .rotate(rotateAnim)
                        .size(24.dp)
                )
            }
        }
    }
}

@Composable
fun CoilImage(
    imageUrl: String
){
    Box (
        modifier = Modifier
            .fillMaxWidth(),
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

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(48.dp)
            .clickable(onClick = { onClick() })
            .background(brush = gradient, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MyAlertDialog(
    title: String,
    textColor: Color,
    text: String,
    confirmText: String,
    isVisible: MutableState<Boolean>
){

    if (isVisible.value) {
        AlertDialog(
            title = { Text(text = title, fontWeight = FontWeight.Bold, color = textColor) },
            text = { Text(text = text, color = textColor) },
            onDismissRequest = { isVisible.value = false },
            confirmButton = {
                TextButton(onClick = {
                    isVisible.value = false
                }) {
                    Text(text = confirmText, color = textColor)
                }
            }
        )
    }
}

