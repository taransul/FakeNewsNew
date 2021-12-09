package com.example.fakenews.data

import com.example.fakenews.data.storage.NewsDao
import com.example.fakenews.domain.NewsInteractor
import com.example.fakenews.domain.NewsInteractorRoom
import com.example.fakenews.presentation.recycler.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsInteractorImpl(private val newsDao: NewsDao) : NewsInteractorRoom{
    override suspend fun getNews(): List<News> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsEntity -> newsEntity.toUser() }
        }
    }

    override suspend fun insertNews(vararg arrayOfNews: News) {
        withContext(Dispatchers.IO) {
            arrayOfNews
                .map { domainNews -> domainNews.toUserEntity() }
                .forEach { newsEntity -> newsDao.insertUser(newsEntity) }
        }
    }
}