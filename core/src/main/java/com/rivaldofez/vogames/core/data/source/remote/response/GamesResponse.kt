package com.rivaldofez.vogames.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rivaldofez.vogames.core.data.source.remote.response.subtype.*

data class GamesResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("nofollow")
	val nofollow: Boolean,

	@field:SerializedName("noindex")
	val noindex: Boolean,

	@field:SerializedName("nofollow_collections")
	val nofollowCollections: List<String>,

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

	@field:SerializedName("reviews_count")
	val reviewsCount: Int
)