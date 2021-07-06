package com.rivaldofez.vogames.core.data.source

import android.util.Log
import com.rivaldofez.core.data.source.remote.RemoteDataSource
import com.rivaldofez.core.data.source.remote.network.ApiResponse
import com.rivaldofez.core.data.source.remote.response.GameListItem
import com.rivaldofez.vogames.core.data.source.local.LocalDataSource
import com.rivaldofez.vogames.core.data.source.local.entity.GameDetailLocalEntity
import com.rivaldofez.vogames.core.data.source.remote.response.GameDetailResponse
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.model.Game
import com.rivaldofez.vogames.core.domain.repository.IGameRepository
import com.rivaldofez.vogames.core.utils.AppExecutors
import com.rivaldofez.vogames.core.utils.DataMapper
import kotlinx.coroutines.flow.*

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

            override suspend fun createCall(): Flow<ApiResponse<List<GameListItem>>> {
                return remoteDataSource.getRecentlyGames()
            }


            override suspend fun saveCallResult(data: List<GameListItem>) {
                val gameList = DataMapper.mapResponsesToLocals(data)
                localDataSource.insertGameList(gameList)
            }
        }.asFlow()

    override fun getFavoriteGames(): Flow<List<DetailGame>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapListDetailLocalToDomain(it)
        }
    }

    override fun getDetailGame(id: String): Flow<Resource<DetailGame?>> =
        object: NetworkBoundResource<DetailGame?, GameDetailResponse>(){
            override fun loadFromDB(): Flow<DetailGame?> {
                val loaded = localDataSource.getDetailGames(id)
                Log.d("Teston", loaded.toString())
                return loaded.map {
                    if(it != null){
                        DataMapper.mapDetailLocalToDomain(it)
                    }else{
                        null
                    }
                }
//                if(loaded.){
//                    Log.d("Teston", "call load")
//                    return loaded.map {
//                        DataMapper.mapDetailLocalToDomain(it)
//                    }
//                }else{
//                    Log.d("Teston", "call null")
//                    return flowOf(null)
//                }
            }

            override fun shouldFetch(data: DetailGame?): Boolean =
                data != null

            override suspend fun createCall(): Flow<ApiResponse<GameDetailResponse>> {
                return remoteDataSource.getDetailGame(id)
            }

            override suspend fun saveCallResult(data: GameDetailResponse) {
                val dataMapped = DataMapper.mapDetailResponseToLocal(data)
                localDataSource.insertDetailGame(dataMapped)
            }
        }.asFlow()


    override fun setFavoriteGame(detailGame: DetailGame, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToLocal(detailGame)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}