package com.rivaldofez.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GamesResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("nofollow")
	val nofollow: Boolean,

	@field:SerializedName("noindex")
	val noindex: Boolean,

	@field:SerializedName("nofollow_collections")
	val nofollowCollections: List<String>,

	@field:SerializedName("previous")
	val previous: Any,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("seo_h1")
	val seoH1: String,

	@field:SerializedName("filters")
	val filters: Filters,

	@field:SerializedName("seo_title")
	val seoTitle: String,

	@field:SerializedName("seo_description")
	val seoDescription: String,

	@field:SerializedName("results")
	val results: List<GameListItem>,

	@field:SerializedName("seo_keywords")
	val seoKeywords: String
)

data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: Int
)

data class AddedByStatus(

	@field:SerializedName("owned")
	val owned: Int,

	@field:SerializedName("beaten")
	val beaten: Int,

	@field:SerializedName("dropped")
	val dropped: Int,

	@field:SerializedName("yet")
	val yet: Int,

	@field:SerializedName("playing")
	val playing: Int,

	@field:SerializedName("toplay")
	val toplay: Int
)

data class ParentPlatformsItem(

	@field:SerializedName("platform")
	val platform: Platform
)

data class Filters(

	@field:SerializedName("years")
	val years: List<YearsItem>
)

data class GameListItem(

	@field:SerializedName("added")
	val added: Int,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("metacritic")
	val metacritic: Int,

	@field:SerializedName("playtime")
	val playtime: Int,

	@field:SerializedName("short_screenshots")
	val shortScreenshots: List<ShortScreenshotsItem>,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>,

	@field:SerializedName("user_game")
	val userGame: Any,

	@field:SerializedName("rating_top")
	val ratingTop: Int,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: Int,

	@field:SerializedName("ratings")
	val ratings: List<RatingsItem>,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("saturated_color")
	val saturatedColor: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("added_by_status")
	val addedByStatus: AddedByStatus,

	@field:SerializedName("parent_platforms")
	val parentPlatforms: List<ParentPlatformsItem>,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int,

	@field:SerializedName("stores")
	val stores: List<StoresItem>,

	@field:SerializedName("tags")
	val tags: List<TagsItem>,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean,

	@field:SerializedName("dominant_color")
	val dominantColor: String,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRating,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("updated")
	val updated: String,

	@field:SerializedName("clip")
	val clip: Any,

	@field:SerializedName("reviews_count")
	val reviewsCount: Int
)

data class RequirementsRu(

	@field:SerializedName("minimum")
	val minimum: String,

	@field:SerializedName("recommended")
	val recommended: String
)

data class Store(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("domain")
	val domain: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,

	@field:SerializedName("slug")
	val slug: String
)

data class PlatformsItem(

	@field:SerializedName("requirements_ru")
	val requirementsRu: Any,

	@field:SerializedName("requirements_en")
	val requirementsEn: Any,

	@field:SerializedName("released_at")
	val releasedAt: String,

	@field:SerializedName("platform")
	val platform: Platform
)

data class TagsItem(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,

	@field:SerializedName("slug")
	val slug: String
)

data class StoresItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("store")
	val store: Store
)

data class EsrbRating(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)

data class YearsItem(

	@field:SerializedName("filter")
	val filter: String,

	@field:SerializedName("nofollow")
	val nofollow: Boolean,

	@field:SerializedName("decade")
	val decade: Int,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("from")
	val from: Int,

	@field:SerializedName("to")
	val to: Int,

	@field:SerializedName("years")
	val years: List<YearsItem>,

	@field:SerializedName("year")
	val year: Int
)

data class RequirementsEn(

	@field:SerializedName("minimum")
	val minimum: String
)

data class Platform(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)

data class RatingsItem(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("percent")
	val percent: Double
)

data class GenresItem(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,

	@field:SerializedName("slug")
	val slug: String
)
