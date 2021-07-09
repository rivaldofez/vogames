package com.rivaldofez.vogames.games

import com.rivaldofez.vogames.core.domain.model.Game

interface GameFragmentCallback {
    fun onGameClick(game: Game)
}