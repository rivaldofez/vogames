package com.rivaldofez.vogames.core.domain.usecase

import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.Game
import kotlinx.coroutines.flow.Flow


interface GameUseCase {
    fun getRecentlyGames() : Flow<Resource<List<Game>>>
}