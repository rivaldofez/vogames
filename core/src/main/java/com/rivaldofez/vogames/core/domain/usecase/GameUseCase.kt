package com.rivaldofez.vogames.core.domain.usecase

import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.model.Game
import kotlinx.coroutines.flow.Flow


interface GameUseCase {
    fun getRecentlyGames() : Flow<Resource<List<Game>>>

    fun getDetailGame(id: String): Flow<Resource<DetailGame?>>

    fun getFavoriteGames(): Flow<List<DetailGame>>

    fun setFavoriteGame(detailGame: DetailGame, state: Boolean)

    fun setScreenshot(screenshot: String, id: String)

    suspend fun getSearchNameResult(query: String): List<Game>?

    suspend fun getSearchNameFavoriteResult(query: String): List<DetailGame>
}