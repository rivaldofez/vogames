package com.rivaldofez.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rivaldofez.core.data.source.local.entity.GameItemLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM gamelist")
    fun getRecentlyGames(): Flow<List<GameItemLocalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameList(gameListLocal: List<GameItemLocalEntity>)
}