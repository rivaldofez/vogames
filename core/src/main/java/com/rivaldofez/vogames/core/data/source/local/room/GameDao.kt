package com.rivaldofez.vogames.core.data.source.local.room

import androidx.room.*
import com.rivaldofez.vogames.core.data.source.local.entity.GameDetailLocalEntity
import com.rivaldofez.vogames.core.data.source.local.entity.GameItemLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM gamelist")
    fun getRecentlyGames(): Flow<List<GameItemLocalEntity>>

    @Query("SELECT * FROM detailgamelist WHERE isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameDetailLocalEntity>>

    @Query("SELECT * FROM detailgamelist WHERE id = :id")
    fun getDetailGame(id: Int): Flow<GameDetailLocalEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameList(gameListLocal: List<GameItemLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailGame(detailGame: GameDetailLocalEntity)

    @Update
    fun updateDetailGame(detailGame: GameDetailLocalEntity)

    @Query("UPDATE detailgamelist set screenshots = :screenshots WHERE id = :id")
    fun setScreenshot(screenshots: String, id: Int)

    @Query("SELECT * FROM gamelist WHERE name like :query")
    suspend fun getSearchNameResult(query: String): List<GameItemLocalEntity>

    @Query("SELECT * FROm detailgamelist WHERE isFavorite = 1 and name like :query")
    suspend fun getSearchNameFavoriteResult(query: String): List<GameDetailLocalEntity>
}