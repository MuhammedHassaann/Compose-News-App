package com.muhammedhassaan.yaganews.screens.home

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedhassaan.domain.model.Article
import com.muhammedhassaan.domain.usecase.GetAllNewsFromLocalUseCase
import com.muhammedhassaan.domain.usecase.SaveNewsUseCase
import com.muhammedhassaan.yaganews.utils.Internet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getAllNewsFromLocalUseCase: GetAllNewsFromLocalUseCase,
    private val application: Application
): ViewModel() {

    val news = mutableStateOf(emptyList<Article>())
    var isLoading by mutableStateOf(false)
    private var job: Job? = null

    fun getNews(){
        job?.cancel()
        isLoading = true
        Log.i("TAG", "getNews: ")
        job = viewModelScope.launch(Dispatchers.IO){
            saveNewsUseCase.invoke()
            news.value = getAllNewsFromLocalUseCase.invoke()
            isLoading = false
        }
    }

    fun cancelRequest(){
        job?.cancel()
        isLoading = false
    }

    fun hasInternet(): Boolean{
        return Internet.isInternetConnected(application.applicationContext)
    }
}