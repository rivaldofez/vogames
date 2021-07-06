package com.rivaldofez.vogames.core.utils

import com.rivaldofez.core.data.source.remote.response.GameListItem
import com.rivaldofez.vogames.core.data.source.local.entity.GameDetailLocalEntity
import com.rivaldofez.vogames.core.data.source.local.entity.GameItemLocalEntity
import com.rivaldofez.vogames.core.data.source.remote.response.GameDetailResponse
import com.rivaldofez.vogames.core.domain.model.DetailGame
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
                ratingTop = it.ratingTop,
                reviewsCount = it.reviewsCount,
                reviewsTextCount = it.reviewsTextCount,
                saturatedColor = it.saturatedColor,
                ratingsCount = it.ratingsCount,
                slug = it.slug,
                released = it.released,
                suggestionsCount = it.suggestionsCount,
                backgroundImage = it.backgroundImage,
                tba = it.tba,
                dominantColor = it.dominantColor,
                name = it.name,
                updated = it.updated,
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

    fun mapListDetailLocalToDomain(input: List<GameDetailLocalEntity>): List<DetailGame> =
        input.map {
            mapDetailLocalToDomain(it)
        }

    fun mapDetailLocalToDomain(input : GameDetailLocalEntity): DetailGame =
        DetailGame(
            id = input.id,
            name = input.name,
            rating = input.rating,
            backgroundImage = input.backgroundImage,
            isFavorite = false
        )

    fun mapDetailResponseToLocal(input: GameDetailResponse): GameDetailLocalEntity =
        GameDetailLocalEntity(
            id = input.id,
            added = input.added,
            nameOriginal = input.nameOriginal,
            rating = input.rating,
            gameSeriesCount = input.gameSeriesCount,
            playtime = input.playtime,
            ratingTop = input.ratingTop,
            reviewsTextCount = input.reviewsTextCount,
            achievementsCount = input.achievementsCount,
            redditName = input.redditName,
            ratingsCount = input.ratingsCount,
            slug = input.slug,
            released = input.released,
            youtubeCount = input.youtubeCount,
            moviesCount = input.moviesCount,
            descriptionRaw = input.descriptionRaw,
            backgroundImage = input.backgroundImage,
            tba = input.tba,
            dominantColor = input.dominantColor,
            name = input.name,
            redditDescription = input.redditDescription,
            redditLogo = input.redditLogo,
            updated = input.updated,
            reviewsCount = input.reviewsCount,
            metacritic = input.metacritic,
            description = input.description,
            metacriticUrl = input.metacriticUrl,
            parentsCount = input.parentsCount,
            creatorsCount = input.creatorsCount,
            saturatedColor = input.saturatedColor,
            redditUrl = input.redditUrl,
            redditCount = input.redditCount,
            parentAchievementsCount = input.parentAchievementsCount,
            website = input.website,
            suggestionsCount = input.suggestionsCount,
            additionsCount = input.additionsCount,
            twitchCount = input.twitchCount,
            backgroundImageAdditional = input.backgroundImageAdditional,
            screenshotsCount = input.screenshotsCount,
        )

    fun mapDetailResponseToDomain(input: GameDetailResponse): DetailGame =
        DetailGame(
            id = input.id,
            backgroundImage = input.backgroundImage,
            rating = input.rating,
            name = input.name,
            isFavorite = false
        )

    fun mapDomainToLocal(input : DetailGame): GameDetailLocalEntity =
        GameDetailLocalEntity(
            id = input.id,
            added = 0,
            nameOriginal = "input.nameOriginal",
            rating = input.rating,
            gameSeriesCount = 0,
            playtime = 0,
            ratingTop = 0,
            reviewsTextCount = 0,
            achievementsCount = 0,
            redditName = "",
            ratingsCount = 0,
            slug = "",
            released = "",
            youtubeCount = 0,
            moviesCount = 0,
            descriptionRaw = "",
            backgroundImage = input.backgroundImage,
            tba = false,
            dominantColor = "",
            name = input.name,
            redditDescription = "",
            redditLogo = "",
            updated = "",
            reviewsCount = 0,
            metacritic = 0,
            description = "",
            metacriticUrl = "",
            parentsCount = 0,
            creatorsCount = 0,
            saturatedColor = "",
            redditUrl = "",
            redditCount = 0,
            parentAchievementsCount = 0,
            website = "",
            suggestionsCount = 0,
            additionsCount = 0,
            twitchCount = 0,
            backgroundImageAdditional = "",
            screenshotsCount = 0,
            isFavorite = false
        )
}