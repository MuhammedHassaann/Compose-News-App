package com.muhammedhassaan.yaganews.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(onFinish: ()->Unit){
    var startAnim by remember{ mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        ), label = ""
    )
    val scaleAnim = animateFloatAsState(
        targetValue = if (startAnim) 1f else 0.5f,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        ), label = ""
    )
    LaunchedEffect(key1 = true){
        startAnim = true
        delay(3000)
        onFinish()
    }
    Splash(alpha = alphaAnim.value, scale = scaleAnim.value)
}

@Composable
fun Splash(
    alpha: Float,
    scale: Float
){
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.Magenta)
            .fillMaxSize()
            .alpha(alpha)
            .scale(scale),
        contentAlignment = Alignment.Center
    ){
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Logo Icon",
            tint = Color.White,
            modifier = Modifier.size(150.dp),
        )
    }
}

@Preview
@Composable
fun PreviewSplashScreen(){
    AnimatedSplashScreen(){

    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewSplashScreenDark(){
    AnimatedSplashScreen(){

    }
}