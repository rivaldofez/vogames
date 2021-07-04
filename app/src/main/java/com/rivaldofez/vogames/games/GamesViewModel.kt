package com.rivaldofez.vogames.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.vogames.core.domain.usecase.GameUseCase

class GamesViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val recentlyGames = gameUseCase.getRecentlyGames().asLiveData()
}

