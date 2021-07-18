package com.rivaldofez.vogames.core.domain.model

data class DetailGame (
    val id: Int,
    val name: String,
    val nameOriginal: String,
    val rating: Double,
    val playtime: Int,
    val released: String,
    val platforms: String,
    val descriptionRaw: String,
    val backgroundImage: String,
    val metacritic: Int,
    val updated: String,
    val description: String,
    val metacriticUrl: String,
    val genres: String,
    val website: String,
    var isFavorite: Boolean = false,
    val publishers: String,
    var screenshots: String,
)