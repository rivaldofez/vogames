package com.rivaldofez.core.domain.repository

import com.rivaldofez.core.data.source.Resource
import com.rivaldofez.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getRecentlyGames(): Flow<Resource<List<Game>>>
}