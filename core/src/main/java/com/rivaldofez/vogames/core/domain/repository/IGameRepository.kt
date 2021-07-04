package com.rivaldofez.vogames.core.domain.repository

import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getRecentlyGames(): Flow<Resource<List<Game>>>
}