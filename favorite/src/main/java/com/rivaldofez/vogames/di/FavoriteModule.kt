package com.rivaldofez.vogames.di


import com.rivaldofez.vogames.favorite.FavoriteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val FavoriteModule = module {
    viewModel{FavoriteViewModel(get())}
}