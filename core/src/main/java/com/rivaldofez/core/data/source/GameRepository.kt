package com.rivaldofez.core.data.source

import com.rivaldofez.core.data.source.local.LocalDataSource
import com.rivaldofez.core.data.source.remote.RemoteDataSource
import com.rivaldofez.core.data.source.remote.network.ApiResponse
import com.rivaldofez.core.data.source.remote.response.GameListItem
import com.rivaldofez.core.data.source.remote.response.GamesResponse
import com.rivaldofez.core.domain.model.Game
import com.rivaldofez.core.domain.repository.IGameRepository
import com.rivaldofez.core.utils.AppExecutors
import com.rivaldofez.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getRecentlyGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameListItem>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getRecentlyGames().map{
                    DataMapper.mapLocalToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameListItem>>> =
                remoteDataSource.getRecentlyGames()


            override suspend fun saveCallResult(data: List<GameListItem>) {
                val gameList = DataMapper.mapResponsesToLocals(data)
                localDataSource.insertGameList(gameList)
            }
        }.asFlow()
}