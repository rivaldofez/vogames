package com.rivaldofez.vogames.core.data.source.remote

import android.util.Log
import com.rivaldofez.vogames.core.data.source.remote.network.ApiResponse
import com.rivaldofez.vogames.core.data.source.remote.network.ApiService
import com.rivaldofez.vogames.core.BuildConfig
import com.rivaldofez.vogames.core.data.source.remote.response.GameDetailResponse
import com.rivaldofez.vogames.core.data.source.remote.response.GameListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getRecentlyGames(): Flow<ApiResponse<List<GameListItem>>> =
        flow {
            try {
                val response = apiService.getRecentlyGames(BuildConfig.API_KEY, "-added")
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                Log.d("Teston", e.toString())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)


    suspend fun getDetailGame(id: String): Flow<ApiResponse<GameDetailResponse>> =
        flow {
            try {
                val response: GameDetailResponse? = apiService.getDetailGame(id = id, key = BuildConfig.API_KEY)
                    if(response != null){
                        emit(ApiResponse.Success(response))
                    }else{
                        emit(ApiResponse.Empty)
                    }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}