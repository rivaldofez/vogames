package com.rivaldofez.core.data.source.remote.network

import com.rivaldofez.core.data.source.remote.response.GamesResponse
import com.rivaldofez.vogames.core.data.source.remote.response.GameDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getRecentlyGames(
        @Query("key") key: String,
        @Query("ordering") ordering: String
    ): GamesResponse

    @GET("games/{id}")
    suspend fun getDetailGame(
        @Path("id") id: String,
        @Query("key") key: String
    ): GameDetailResponse
}