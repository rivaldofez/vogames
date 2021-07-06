package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class MetacriticPlatformsItem(

    @field:SerializedName("metascore")
    val metascore: Int,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("platform")
    val platform: PlatformDetail
)