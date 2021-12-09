package com.example.fakenews.data

import com.example.fakenews.data.storage.NewsEntity
import com.example.fakenews.presentation.recycler.News

fun NewsEntity.toUser() =
    News(
        title = title,
        author = author,
        date = date,
        topic = topic,
        text = text
    )

fun News.toUserEntity() =
    NewsEntity(
        title = title,
        author = author,
        date = date,
        topic = topic,
        text = text
    )
