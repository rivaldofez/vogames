package com.rivaldofez.vogames.di


import com.rivaldofez.vogames.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FavoriteModule = module {
    viewModel{FavoriteViewModel(get())}
}