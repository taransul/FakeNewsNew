package com.example.fakenews.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakenews.domain.NewsInteractor
import com.example.fakenews.presentation.recycler.News
import kotlinx.coroutines.launch

class NewsFragmentViewModel(
    private val interactor: NewsInteractor
) : ViewModel() {

    private val _passesListViewModel = MutableLiveData<List<News>>()
    val passesListViewModel: LiveData<List<News>> get() = _passesListViewModel

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = _newsList

    fun loadMessages(load: List<News>) {
        viewModelScope.launch {
            _passesListViewModel.value = interactor.loadMessages(load)
        }
    }

    fun newsList() {
        viewModelScope.launch {
            _newsList.value = interactor.newsList()
        }
    }
}