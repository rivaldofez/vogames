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


//    @ColumnInfo(name = "game_series_count")
//    val gameSeriesCount: Int,
//
//    @ColumnInfo(name = "platforms")
//    val platforms: List<PlatformsItem>,
//
//    @ColumnInfo(name = "rating_top")
//    val ratingTop: Int,
//
//    @ColumnInfo(name = "reviews_text_count")
//    val reviewsTextCount: Int,
//
//    @ColumnInfo(name = "publishers")
//    val publishers: List<PublishersItem>,
//
//    @ColumnInfo(name = "achievements_count")
//    val achievementsCount: Int,
//
//    @ColumnInfo(name = "parent_platforms")
//    val parentPlatforms: List<ParentPlatformsItem>,
//
//    @ColumnInfo(name = "reddit_name")
//    val redditName: String,
//
//    @ColumnInfo(name = "ratings_count")
//    val ratingsCount: Int,
//
//    @ColumnInfo(name = "slug")
//    val slug: String,

//    @ColumnInfo(name = "youtube_count")
//    val youtubeCount: Int,
//
//    @ColumnInfo(name = "movies_count")
//    val moviesCount: Int,
//
//    @ColumnInfo(name = "tags")
//    val tags: List<TagsItem>,
//
//    @ColumnInfo(name = "tba")
//    val tba: Boolean,
//
//    @ColumnInfo(name = "dominant_color")
//    val dominantColor: String,
//
//    @ColumnInfo(name = "reddit_description")
//    val redditDescription: String,
//
//    @ColumnInfo(name = "reddit_logo")
//    val redditLogo: String,
//
//    @ColumnInfo(name = "reviews_count")
//    val reviewsCount: Int,
//
//    @ColumnInfo(name = "alternative_names")
//    val alternativeNames: List<String>,
//
//    @ColumnInfo(name = "parents_count")
//    val parentsCount: Int,
//
//    @ColumnInfo(name = "user_game")
//    val userGame: Any,
//
//    @ColumnInfo(name = "metacritic_platforms")
//    val metacriticPlatforms: List<MetacriticPlatformsItem>,
//
//    @ColumnInfo(name = "creators_count")
//    val creatorsCount: Int,
//
//    @ColumnInfo(name = "ratings")
//    val ratings: List<RatingsItem>,
//
//    @ColumnInfo(name = "saturated_color")
//    val saturatedColor: String,
//
//    @ColumnInfo(name = "added_by_status")
//    val addedByStatus: AddedByStatus,
//
//    @ColumnInfo(name = "reddit_url")
//    val redditUrl: String,
//
//    @ColumnInfo(name = "reddit_count")
//    val redditCount: Int,
//
//    @ColumnInfo(name = "parent_achievements_count")
//    val parentAchievementsCount: Int,
//
//    @ColumnInfo(name = "suggestions_count")
//    val suggestionsCount: Int,
//
//    @ColumnInfo(name = "stores")
//    val stores: List<StoresItem>,
//
//    @ColumnInfo(name = "additions_count")
//    val additionsCount: Int,
//
//    @ColumnInfo(name = "twitch_count")
//    val twitchCount: Int,
//
//    @ColumnInfo(name = "background_image_additional")
//    val backgroundImageAdditional: String,
//
//    @ColumnInfo(name = "esrb_rating")
//    val esrbRating: EsrbRating,
//
//    @ColumnInfo(name = "screenshots_count")
//    val screenshotsCount: Int,
//
//    @ColumnInfo(name = "reactions")
//    val reactions: Reactions,
//
//    @ColumnInfo(name = "clip")
//    val clip: Any

)