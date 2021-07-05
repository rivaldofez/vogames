package com.rivaldofez.vogames.core.domain.repository

import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getRecentlyGames(): Flow<Resource<List<Game>>>

    fun getDetailGame(id: String): Flow<Resource<DetailGame?>>

    fun getFavoriteGames(): Flow<List<DetailGame>>

    fun setFavoriteGame(detailGame: DetailGame, state: Boolean)
}