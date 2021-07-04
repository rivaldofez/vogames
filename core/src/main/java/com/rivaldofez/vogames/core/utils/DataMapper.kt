package com.rivaldofez.vogames.core.utils

import com.rivaldofez.core.data.source.remote.response.GameListItem
import com.rivaldofez.vogames.core.data.source.local.entity.GameItemLocalEntity
import com.rivaldofez.vogames.core.domain.model.Game

object DataMapper {
    fun mapResponsesToLocals(input: List<GameListItem>): List<GameItemLocalEntity> {
        val gameList = ArrayList<GameItemLocalEntity>()

        input.map {
            val game = GameItemLocalEntity(
                id = it.id,
                added = it.added,
                rating = it.rating,
                metacritic = it.metacritic,
                playtime = it.playtime,
//                userGame = it.userGame,
                ratingTop = it.ratingTop,
                reviewsCount = it.reviewsCount,
                reviewsTextCount = it.reviewsTextCount,
                saturatedColor = it.saturatedColor,
//                addedByStatus = it.addedByStatus,
                ratingsCount = it.ratingsCount,
                slug = it.slug,
                released = it.released,
                suggestionsCount = it.suggestionsCount,
                backgroundImage = it.backgroundImage,
                tba = it.tba,
                dominantColor = it.dominantColor,
//                esrbRating = it.esrbRating,
                name = it.name,
                updated = it.updated,
//                clip = it.clip
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapLocalToDomain(input: List<GameItemLocalEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                rating = it.rating,
                backgroundImage = it.backgroundImage
            )
        }

//    fun mapDomainToLocal(input: Game) = GameItemLocalEntity(
//        id = input.id,
//        name = input.name,
//        rating = input.rating
//    )
}