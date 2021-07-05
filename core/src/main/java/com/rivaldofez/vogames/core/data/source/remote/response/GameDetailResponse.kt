package com.rivaldofez.vogames.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(

	@field:SerializedName("added")
	val added: Int,

	@field:SerializedName("developers")
	val developers: List<DevelopersItem>,

	@field:SerializedName("name_original")
	val nameOriginal: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("game_series_count")
	val gameSeriesCount: Int,

	@field:SerializedName("playtime")
	val playtime: Int,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>,

	@field:SerializedName("rating_top")
	val ratingTop: Int,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: Int,

	@field:SerializedName("publishers")
	val publishers: List<PublishersItem>,

	@field:SerializedName("achievements_count")
	val achievementsCount: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("parent_platforms")
	val parentPlatforms: List<ParentPlatformsItem>,

	@field:SerializedName("reddit_name")
	val redditName: String,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("youtube_count")
	val youtubeCount: Int,

	@field:SerializedName("movies_count")
	val moviesCount: Int,

	@field:SerializedName("description_raw")
	val descriptionRaw: String,

	@field:SerializedName("tags")
	val tags: List<TagsItem>,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean,

	@field:SerializedName("dominant_color")
	val dominantColor: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("reddit_description")
	val redditDescription: String,

	@field:SerializedName("reddit_logo")
	val redditLogo: String,

	@field:SerializedName("updated")
	val updated: String,

	@field:SerializedName("reviews_count")
	val reviewsCount: Int,

	@field:SerializedName("metacritic")
	val metacritic: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("metacritic_url")
	val metacriticUrl: String,

	@field:SerializedName("alternative_names")
	val alternativeNames: List<String>,

	@field:SerializedName("parents_count")
	val parentsCount: Int,

	@field:SerializedName("user_game")
	val userGame: Any,

	@field:SerializedName("metacritic_platforms")
	val metacriticPlatforms: List<MetacriticPlatformsItem>,

	@field:SerializedName("creators_count")
	val creatorsCount: Int,

	@field:SerializedName("ratings")
	val ratings: List<RatingsItem>,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("saturated_color")
	val saturatedColor: String,

	@field:SerializedName("added_by_status")
	val addedByStatus: AddedByStatus,

	@field:SerializedName("reddit_url")
	val redditUrl: String,

	@field:SerializedName("reddit_count")
	val redditCount: Int,

	@field:SerializedName("parent_achievements_count")
	val parentAchievementsCount: Int,

	@field:SerializedName("website")
	val website: String,

	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int,

	@field:SerializedName("stores")
	val stores: List<StoresItem>,

	@field:SerializedName("additions_count")
	val additionsCount: Int,

	@field:SerializedName("twitch_count")
	val twitchCount: Int,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRating,

	@field:SerializedName("screenshots_count")
	val screenshotsCount: Int,

	@field:SerializedName("reactions")
	val reactions: Reactions,

	@field:SerializedName("clip")
	val clip: Any
)

data class Platform(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("platform")
	val platform: Int,

	@field:SerializedName("slug")
	val slug: String
)

data class MetacriticPlatformsItem(

	@field:SerializedName("metascore")
	val metascore: Int,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("platform")
	val platform: Platform
)

data class Requirements(

	@field:SerializedName("minimum")
	val minimum: String,

	@field:SerializedName("recommended")
	val recommended: String
)

data class Reactions(

	@field:SerializedName("11")
	val jsonMember11: Int,

	@field:SerializedName("12")
	val jsonMember12: Int,

	@field:SerializedName("14")
	val jsonMember14: Int,

	@field:SerializedName("15")
	val jsonMember15: Int,

	@field:SerializedName("16")
	val jsonMember16: Int,

	@field:SerializedName("18")
	val jsonMember18: Int,

	@field:SerializedName("1")
	val jsonMember1: Int,

	@field:SerializedName("2")
	val jsonMember2: Int,

	@field:SerializedName("3")
	val jsonMember3: Int,

	@field:SerializedName("4")
	val jsonMember4: Int,

	@field:SerializedName("5")
	val jsonMember5: Int,

	@field:SerializedName("6")
	val jsonMember6: Int,

	@field:SerializedName("7")
	val jsonMember7: Int,

	@field:SerializedName("8")
	val jsonMember8: Int,

	@field:SerializedName("9")
	val jsonMember9: Int,

	@field:SerializedName("20")
	val jsonMember20: Int,

	@field:SerializedName("10")
	val jsonMember10: Int,

	@field:SerializedName("21")
	val jsonMember21: Int
)

data class ParentPlatformsItem(

	@field:SerializedName("platform")
	val platform: Platform
)

data class StoresItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("store")
	val store: Store,

	@field:SerializedName("url")
	val url: String
)

data class PlatformsItem(

	@field:SerializedName("requirements")
	val requirements: Requirements,

	@field:SerializedName("released_at")
	val releasedAt: String,

	@field:SerializedName("platform")
	val platform: Platform
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

data class DevelopersItem(

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

data class PublishersItem(

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

data class EsrbRating(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)
