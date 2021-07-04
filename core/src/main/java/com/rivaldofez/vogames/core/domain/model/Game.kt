package com.rivaldofez.vogames.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game (
    val id: Int,
    val name: String,
    val rating: Double,
    val backgroundImage: String
): Parcelable