package com.rivaldofez.vogames.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailGame (
    val id: Int,
    val name: String,
    val rating: Double,
    val backgroundImage: String,
    var isFavorite: Boolean
): Parcelable