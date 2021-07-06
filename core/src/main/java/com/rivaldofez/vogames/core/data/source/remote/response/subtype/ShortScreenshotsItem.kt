package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class ShortScreenshotsItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("id")
    val id: Int
)