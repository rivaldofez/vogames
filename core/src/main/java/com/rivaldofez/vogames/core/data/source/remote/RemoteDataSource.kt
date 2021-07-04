package com.rivaldofez.core.data.source.remote

import android.util.Log
import com.rivaldofez.core.data.source.remote.network.ApiResponse
import com.rivaldofez.core.data.source.remote.network.ApiService
import com.rivaldofez.core.data.source.remote.response.GameListItem
import com.rivaldofez.vogames.core.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getRecentlyGames(): Flow<ApiResponse<List<GameListItem>>> {
        //get data from api
        return flow {
            try {
                val response = apiService.getRecentlyGames(BuildConfig.API_KEY, "added")
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}