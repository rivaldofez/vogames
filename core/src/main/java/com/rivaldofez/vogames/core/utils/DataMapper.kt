package com.rivaldofez.vogames.core.utils

import com.rivaldofez.vogames.core.data.source.local.entity.GameDetailLocalEntity
import com.rivaldofez.vogames.core.data.source.local.entity.GameItemLocalEntity
import com.rivaldofez.vogames.core.data.source.remote.response.GameDetailResponse
import com.rivaldofez.vogames.core.data.source.remote.response.GameListItem
import com.rivaldofez.vogames.core.data.source.remote.response.subtype.*
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.model.Game

object DataMapper {

    fun mapListScreenshotToString(input: List<ShortScreenshotsItem>): String =
        input.joinToString { it.image }

    fun mapListParentPlatformToString(input: List<ParentPlatformsItem>): String =
        input.joinToString { it.platform.name }

    fun mapGenresToString(input: List<GenresItem>): String =
        input.joinToString { it.name }

    fun mapListPlatformDetailToString(input: List<ParentPlatformsItemDetail>): String =
        input.joinToString { it.platform.name }

    fun mapListPublisherToString(input: List<PublishersItem>): String =
        input.joinToString { it.name }

    fun mapListResponseToLocal(input: List<GameListItem>): List<GameItemLocalEntity> {
        val gameList = ArrayList<GameItemLocalEntity>()
        input.map {
            val shortScreenshots = mapListScreenshotToString(it.shortScreenshots)
            val parentPlatforms = mapListParentPlatformToString(it.parentPlatforms)
            val genres = mapGenresToString(it.genres)
            val game = GameItemLocalEntity(
                id = it.id,
                rating = it.rating,
                metacritic = it.metacritic,
                playtime = it.playtime,
                shortScreenshots = shortScreenshots,
                parentPlatforms = parentPlatforms,
                genres = genres,
                backgroundImage = it.backgroundImage,
                name = it.name,
                updated = it.updated
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapListDetailLocalToDomain(input: List<GameDetailLocalEntity>): List<DetailGame> =
        input.map {
            mapDetailLocalToDomain(it)
        }

    fun mapDetailResponseToLocal(input: GameDetailResponse): GameDetailLocalEntity =
        GameDetailLocalEntity(
            id = input.id,
            name = input.name,
            nameOriginal = input.nameOriginal,
            rating = input.rating,
            playtime = input.playtime,
            released = input.released,
            platforms = mapListPlatformDetailToString(input.parentPlatforms),
            descriptionRaw = input.descriptionRaw,
            backgroundImage = input.backgroundImage,
            metacritic = input.metacritic,
            updated = input.updated,
            description = input.description,
            metacriticUrl = input.metacriticUrl,
            genres = mapGenresToString(input.genres),
            website = input.website,
            isFavorite = false,
            publishers = mapListPublisherToString(input.publishers)
        )

    fun mapLocalToDomain(input: List<GameItemLocalEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                rating = it.rating,
                backgroundImage = it.backgroundImage
            )
        }

    fun mapDetailLocalToDomain(input : GameDetailLocalEntity): DetailGame =
        DetailGame(
            id = input.id,
            name = input.name,
            rating = input.rating,
            backgroundImage = input.backgroundImage,
            isFavorite = false
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