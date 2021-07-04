package com.rivaldofez.vogames.core.ui

import com.rivaldofez.vogames.core.domain.model.Game

interface GameFragmentCallback {
    fun onGameClick(game: Game)
}