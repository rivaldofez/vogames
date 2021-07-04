package com.rivaldofez.vogames.core.domain.usecase


import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.Game
import com.rivaldofez.vogames.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getRecentlyGames(): Flow<Resource<List<Game>>> = gameRepository.getRecentlyGames()
}