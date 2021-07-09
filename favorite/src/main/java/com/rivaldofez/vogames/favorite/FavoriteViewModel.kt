package com.rivaldofez.vogames.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.usecase.GameUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class FavoriteViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    val favoriteGames = gameUseCase.getFavoriteGames().asLiveData()

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchResult = queryChannel.asFlow()
            .distinctUntilChanged()
            .debounce(300)
            .filter {
                it.trim().isNotEmpty()
            }
            .mapLatest {
                gameUseCase.getSearchNameFavoriteResult(it)
            }.asLiveData()

    fun setFavoriteGame(detailGame: DetailGame, state: Boolean) = gameUseCase.setFavoriteGame(detailGame, state)
}