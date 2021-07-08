package com.rivaldofez.vogames.di

import com.rivaldofez.vogames.core.domain.usecase.GameInteractor
import com.rivaldofez.vogames.core.domain.usecase.GameUseCase
import com.rivaldofez.vogames.detail.DetailGameViewModel
import com.rivaldofez.vogames.games.GamesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel{ GamesViewModel(get()) }
    viewModel{DetailGameViewModel(get())}
}