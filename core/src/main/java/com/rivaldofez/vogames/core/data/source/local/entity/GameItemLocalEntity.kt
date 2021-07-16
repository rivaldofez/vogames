package com.rivaldofez.vogames.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gamelist")
data class GameItemLocalEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "metacritic")
    val metacritic: Int,

    @ColumnInfo(name = "playtime")
    val playtime: Int,

    @ColumnInfo(name = "short_screenshots")
    val shortScreenshots: String,

    @ColumnInfo(name = "parent_platforms")
    val parentPlatforms: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "updated")
    val updated: String
)