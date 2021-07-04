package com.rivaldofez.core.data.source.remote.network

import com.rivaldofez.core.data.source.remote.response.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getRecentlyGames(
        @Query("key") key: String,
        @Query("ordering") ordering: String
    ): GamesResponse
}