package com.rivaldofez.vogames.core.domain.usecase


import android.util.Log
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.domain.model.Game
import com.rivaldofez.vogames.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getRecentlyGames(): Flow<Resource<List<Game>>> = gameRepository.getRecentlyGames()
    override fun getDetailGame(id: String): Flow<Resource<DetailGame?>> {
        Log.d("Teston", "terpanggil di interactor")
        return gameRepository.getDetailGame(id)
    }
    override fun getFavoriteGames(): Flow<List<DetailGame>> = gameRepository.getFavoriteGames()
    override fun setFavoriteGame(detailGame: DetailGame, state: Boolean) = gameRepository.setFavoriteGame(detailGame, state)
    override fun setScreenshot(screenshot: String, id: String) = gameRepository.setScreenshot(screenshot, id)
    override suspend fun getSearchNameResult(keyword: String) = gameRepository.getSearchNameResult(keyword)
}