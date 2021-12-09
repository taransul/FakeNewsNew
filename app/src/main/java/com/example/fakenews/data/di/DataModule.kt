package com.example.fakenews.data.di

import androidx.room.Room
import com.example.fakenews.data.DataSource
import com.example.fakenews.data.storage.AppDatabase
import com.example.fakenews.domain.NewsInteractor
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val DATA_SOURCE_QUALIFIER: Qualifier = qualifier("DATA_SOURCE_QUALIFIER")

val dataModule = module {
    single<NewsInteractor>(qualifier = DATA_SOURCE_QUALIFIER) {
        DataSource()
    }

    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "news"
        ).build()
    }

    single {
        get<AppDatabase>().getUserDao()
    }
}