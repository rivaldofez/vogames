package com.rivaldofez.vogames.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.usecase.GameUseCase

class DetailGameViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun getDetailGame(id: String) : LiveData<Resource<DetailGame?>> {
       return gameUseCase.getDetailGame(id).asLiveData()
    }

    fun setFavoriteGame(detailGame: DetailGame, state: Boolean) = gameUseCase.setFavoriteGame(detailGame, state)

    fun setScreenshot(screenshot: String, id: String) = gameUseCase.setScreenshot(screenshot, id)

}