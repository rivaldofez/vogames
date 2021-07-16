package com.rivaldofez.vogames.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "detailgamelist")
data class GameDetailLocalEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "name_original")
    val nameOriginal: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "playtime")
    val playtime: Int,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "platforms")
    val platforms: String,

    @ColumnInfo(name = "description_raw")
    val descriptionRaw: String,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String,

    @ColumnInfo(name = "metacritic")
    val metacritic: Int,

    @ColumnInfo(name = "updated")
    val updated: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "metacritic_url")
    val metacriticUrl: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "website")
    val website: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "publishers")
    val publishers: String,

    @ColumnInfo(name = "screenshots")
    var screenshots: String? = null,
)