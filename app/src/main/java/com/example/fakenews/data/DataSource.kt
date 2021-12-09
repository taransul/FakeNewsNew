package com.example.fakenews.data

import com.example.fakenews.domain.NewsInteractor
import com.example.fakenews.presentation.recycler.News

class DataSource : NewsInteractor {
    override fun loadMessages(filter: List<News>): List<News> {
        return filter
    }

    override fun newsList(): List<News> {
        return list
    }

    private val list: List<News> = listOf(
        News(
            "title1",
            "author1",
            "26.11.2021",
            "Политика",
            "Текст"
        ),
        News(
            "title2",
            "author3",
            "19.11.2021",
            "Технологии",
            "Текст"
        ),
        News(
            "title3",
            "author2",
            "19.11.2021",
            "Игры",
            "Текст"
        ),
        News(
            "title4",
            "author1",
            "26.10.2021",
            "Экономика",
            "Текст"
        ),
        News(
            "title5",
            "author4",
            "25.11.2021",
            "Политика",
            "Текст"
        ),
        News(
            "title6",
            "author4",
            "19.11.2021",
            "Технологии",
            "Текст"
        ),
        News(
            "title7",
            "author1",
            "26.10.2021",
            "Игры",
            "Текст"
        ),
        News(
            "title8",
            "author3",
            "26.11.2021",
            "Экономика",
            "Текст"
        ),
        News(
            "title9",
            "author3",
            "25.11.2021",
            "Политика",
            "Текст"
        ),
        News(
            "title10",
            "author1",
            "26.10.2021",
            "Технологии",
            "Текст"
        ),
        News(
            "title11",
            "author2",
            "19.11.2021",
            "Игры",
            "Текст"
        ),
        News(
            "title12",
            "author4",
            "25.11.2021",
            "Экономика",
            "Текст"
        ),
        News(
            "title13",
            "author4",
            "26.11.2021",
            "Политика",
            "Текст"
        ),
        News(
            "title14",
            "author3",
            "25.11.2021",
            "Технологии",
            "Текст"
        ),
        News(
            "title15",
            "author2",
            "26.10.2021",
            "Игры",
            "Текст"
        ),
        News(
            "title16",
            "author2",
            "26.11.2021",
            "Экономика",
            "Текст"
        )
    )
}