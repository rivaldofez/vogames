package com.rivaldofez.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rivaldofez.core.data.source.local.entity.GameItemLocalEntity

@Database(entities = [GameItemLocalEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase(): RoomDatabase() {
    abstract fun gameDao(): GameDao

}