package com.rivaldofez.vogames.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.vogames.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase): ViewModel() {
    val favoriteGames = gameUseCase.getFavoriteGames().asLiveData()
}