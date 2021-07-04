package com.rivaldofez.core.data.source.local

import com.rivaldofez.core.data.source.local.entity.GameItemLocalEntity
import com.rivaldofez.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {
    fun getRecentlyGames(): Flow<List<GameItemLocalEntity>> = gameDao.getRecentlyGames()

    suspend fun insertGameList(gameListLocal: List<GameItemLocalEntity>) = gameDao.insertGameList(gameListLocal)

}