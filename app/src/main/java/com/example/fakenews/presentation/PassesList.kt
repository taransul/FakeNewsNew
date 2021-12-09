package com.example.fakenews.presentation

import com.example.fakenews.presentation.recycler.News

interface PassesList {
    fun displaysListOnScreen(filter: List<News>, selectionInformation: String)
}