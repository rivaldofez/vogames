package com.rivaldofez.vogames.core.data.source.local

import com.rivaldofez.vogames.core.data.source.local.entity.GameDetailLocalEntity
import com.rivaldofez.vogames.core.data.source.local.entity.GameItemLocalEntity
import com.rivaldofez.vogames.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {
    fun getRecentlyGames(): Flow<List<GameItemLocalEntity>> = gameDao.getRecentlyGames()

    fun getDetailGames(id: String): Flow<GameDetailLocalEntity?> = gameDao.getDetailGame(id.toInt())

    fun getFavoriteGames(): Flow<List<GameDetailLocalEntity>> = gameDao.getFavoriteGames()

    suspend fun insertGameList(gameListLocal: List<GameItemLocalEntity>) = gameDao.insertGameList(gameListLocal)

    suspend fun insertDetailGame(detailGame: GameDetailLocalEntity) = gameDao.insertDetailGame(detailGame)

    fun setFavoriteGame(detailGame: GameDetailLocalEntity, newState: Boolean) {
        detailGame.isFavorite = newState
        gameDao.updateDetailGame(detailGame)
    }
    
    fun setScreenshot(screenshots: String, id: String) = gameDao.setScreenshot(screenshots, id.toInt())

    suspend fun getSearchNameResult(query: String) = gameDao.getSearchNameResult("%$query%")

}