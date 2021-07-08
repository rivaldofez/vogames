package com.rivaldofez.vogames.core.domain.model

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
)