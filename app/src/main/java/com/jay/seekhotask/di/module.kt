package com.jay.seekhotask.di

import android.util.Log
import com.jay.seekhotask.model.repository.AnimeDetailsRepository
import com.jay.seekhotask.model.repository.AnimeDetailsRepositoryImpl
import com.jay.seekhotask.model.repository.AnimeListRepository
import com.jay.seekhotask.model.repository.AnimeListRepositoryImpl
import com.jay.seekhotask.viewmodel.AnimeDetailsViewModel
import com.jay.seekhotask.viewmodel.AnimeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val module = module {
    single<AnimeListRepository> {
        Log.d("KoinModule", "AnimeList Repository initialized")
        AnimeListRepositoryImpl()
    }
    viewModel { AnimeViewModel(get()) }

    single<AnimeDetailsRepository> {
        Log.d("KoinModule", "AnimeList Repository initialized")
        AnimeDetailsRepositoryImpl()
    }
    viewModel { AnimeDetailsViewModel(get()) }

}