package com.rivaldofez.vogames.core.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game (
    val id: Int,
    val rating: Double,
    val metacritic: Int,
    val playtime: Int,
    val shortScreenshots: String,
    val parentPlatforms: String,
    val genres: String,
    val backgroundImage: String,
    val name: String,
    val updated: String
): Parcelable