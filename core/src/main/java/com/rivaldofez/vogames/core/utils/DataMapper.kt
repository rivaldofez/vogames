package com.rivaldofez.vogames.core.utils

import com.rivaldofez.vogames.core.data.source.local.entity.GameDetailLocalEntity
import com.rivaldofez.vogames.core.data.source.local.entity.GameItemLocalEntity
import com.rivaldofez.vogames.core.data.source.remote.response.GameDetailResponse
import com.rivaldofez.vogames.core.data.source.remote.response.GameListItem
import com.rivaldofez.vogames.core.data.source.remote.response.subtype.*
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.model.Game
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object DataMapper {

    private fun mapListScreenshotToString(input: List<ShortScreenshotsItem>): String =
        input.joinToString { it.image }

    private fun mapListParentPlatformToString(input: List<ParentPlatformsItem>): String =
        input.joinToString { it.platform.name }

    private fun mapGenresToString(input: List<GenresItem>): String =
        input.joinToString { it.name }

    private fun mapListPlatformDetailToString(input: List<ParentPlatformsItemDetail>): String =
        input.joinToString { it.platform.name }

    private fun mapListPublisherToString(input: List<PublishersItem>): String =
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
            publishers = mapListPublisherToString(input.publishers)
        )

    fun mapListLocalToDomain(input: List<GameItemLocalEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                rating = it.rating,
                metacritic = it.metacritic,
                playtime = it.playtime,
                shortScreenshots = it.shortScreenshots,
                parentPlatforms = it.parentPlatforms,
                genres = it.genres,
                backgroundImage = it.backgroundImage,
                name = it.name,
                updated = it.updated
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
            nameOriginal = input.nameOriginal,
            rating = input.rating,
            playtime = input.playtime,
            released = input.released,
            platforms = input.platforms,
            descriptionRaw = input.descriptionRaw,
            backgroundImage = input.backgroundImage,
            metacritic = input.metacritic,
            updated = input.updated,
            description = input.description,
            metacriticUrl = input.metacriticUrl,
            genres = input.genres,
            website = input.website,
            isFavorite = input.isFavorite,
            publishers = input.publishers,
            screenshots = input.screenshots
        )

    fun mapDomainToLocal(input : DetailGame): GameDetailLocalEntity =
        GameDetailLocalEntity(
            id = input.id,
            name = input.name,
            nameOriginal = input.nameOriginal,
            rating = input.rating,
            playtime = input.playtime,
            released = input.released,
            platforms = input.platforms,
            descriptionRaw = input.descriptionRaw,
            backgroundImage = input.backgroundImage,
            metacritic = input.metacritic,
            updated = input.updated,
            description = input.description,
            metacriticUrl = input.metacriticUrl,
            genres = input.genres,
            website = input.website,
            isFavorite = input.isFavorite,
            publishers = input.publishers,
            screenshots = input.screenshots
        )

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun formatDate(value: String): String{
        return try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(value)
            SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(date)

        } catch (e: ParseException) {
            print("Error while parse date" + e.message.toString())
            "-"
        }
    }
}