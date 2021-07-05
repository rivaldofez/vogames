package com.rivaldofez.vogames.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.usecase.GameUseCase

class DetailGameViewModel(val gameUseCase: GameUseCase): ViewModel() {
    private var gameId: String? = null

    fun setCurrentGame(gameId: String){
        this.gameId = gameId
        Log.d("Teston", "Message " + this.gameId)
    }

    fun getDetailGame(id: String) : LiveData<Resource<DetailGame?>> {
       return gameUseCase.getDetailGame(id).asLiveData()
    }

}