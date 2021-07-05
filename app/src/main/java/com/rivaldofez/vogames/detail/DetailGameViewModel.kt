package com.rivaldofez.vogames.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.vogames.core.domain.usecase.GameUseCase

class DetailGameViewModel(gameUseCase: GameUseCase): ViewModel() {
    private var gameId: String? = null

    fun setCurrentGame(gameId: String){
        this.gameId = gameId
    }

    val detailGame = gameId?.let { gameUseCase.getDetailGame(it).asLiveData() }
}