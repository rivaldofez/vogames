package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class PlatformsItemDetail(

    @field:SerializedName("requirements")
    val requirements: Requirements,

    @field:SerializedName("released_at")
    val releasedAt: String,

    @field:SerializedName("platform")
    val platform: PlatformDetail
)