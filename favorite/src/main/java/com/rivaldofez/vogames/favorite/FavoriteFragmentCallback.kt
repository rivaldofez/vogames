package com.rivaldofez.vogames.favorite

import com.rivaldofez.vogames.core.domain.model.DetailGame

interface FavoriteFragmentCallback {
    fun onFavoriteItemClick(favoriteItem : DetailGame)
}