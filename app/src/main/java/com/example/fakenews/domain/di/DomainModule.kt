package com.example.fakenews.domain.di

import com.example.fakenews.data.NewsInteractorImpl
import com.example.fakenews.domain.NewsInteractorRoom
import org.koin.dsl.module

val domainModule = module {
    single<NewsInteractorRoom> {
        NewsInteractorImpl(newsDao = get())
    }
}