package com.example.fakenews.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakenews.domain.NewsInteractorRoom
import com.example.fakenews.presentation.recycler.News
import kotlinx.coroutines.launch

class NewsViewModelRoom(
    private val newsInteractorRoom: NewsInteractorRoom
) : ViewModel() {

    private val _newsLoad = MutableLiveData<List<News>>()
    val newsLoad: LiveData<List<News>> get() = _newsLoad

    fun loadNews() {
        viewModelScope.launch {
            _newsLoad.value = newsInteractorRoom.getNews()
        }
    }

    fun insertNews() {
        viewModelScope.launch {
            newsInteractorRoom.insertNews(
                News(
                    "title1",
                    "author1",
                    "26.11.2021",
                    "Политика",
                    "ТЕКСТ"
                )
            )
        }
    }
}