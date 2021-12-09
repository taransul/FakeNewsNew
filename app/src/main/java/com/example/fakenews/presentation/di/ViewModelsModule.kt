package com.example.fakenews.presentation.di

import com.example.fakenews.data.di.DATA_SOURCE_QUALIFIER
import com.example.fakenews.presentation.NewsFragmentViewModel
import com.example.fakenews.presentation.NewsViewModelRoom
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        NewsFragmentViewModel(
            interactor = get(qualifier = DATA_SOURCE_QUALIFIER)
        )
    }
    viewModel {
        NewsViewModelRoom(get())
    }
}