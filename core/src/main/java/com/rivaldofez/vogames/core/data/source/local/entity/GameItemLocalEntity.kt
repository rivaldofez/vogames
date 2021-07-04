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

    @ColumnInfo(name = "added")
    val added: Int,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "metacritic")
    val metacritic: Int,

    @ColumnInfo(name = "playtime")
    val playtime: Int,

//    @ColumnInfo(name = "short_screenshots")
//    val shortScreenshots: List<ShortScreenshotsItem>,

//    @ColumnInfo(name = "platforms")
//    val platforms: List<PlatformsItem>,

//    @ColumnInfo(name = "user_game")
//    val userGame: Any,

    @ColumnInfo(name = "rating_top")
    val ratingTop: Int,

    @ColumnInfo(name = "reviews_text_count")
    val reviewsTextCount: Int,

//    @ColumnInfo(name = "ratings")
//    val ratings: List<RatingsItem>,
//
//    @ColumnInfo(name = "genres")
//    val genres: List<GenresItem>,

    @ColumnInfo(name = "saturated_color")
    val saturatedColor: String,

//    @ColumnInfo(name = "added_by_status")
//    val addedByStatus: AddedByStatus,

//    @ColumnInfo(name = "parent_platforms")
//    val parentPlatforms: List<ParentPlatformsItem>,

    @ColumnInfo(name = "ratings_count")
    val ratingsCount: Int,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "suggestions_count")
    val suggestionsCount: Int,

//    @ColumnInfo(name = "stores")
//    val stores: List<StoresItem>,
//
//    @ColumnInfo(name = "tags")
//    val tags: List<TagsItem>,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String,

    @ColumnInfo(name = "tba")
    val tba: Boolean,

    @ColumnInfo(name = "dominant_color")
    val dominantColor: String,

//    @ColumnInfo(name = "esrb_rating")
//    val esrbRating: EsrbRating,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "updated")
    val updated: String,

//    @ColumnInfo(name = "clip")
//    val clip: Any,

    @ColumnInfo(name = "reviews_count")
    val reviewsCount: Int

)