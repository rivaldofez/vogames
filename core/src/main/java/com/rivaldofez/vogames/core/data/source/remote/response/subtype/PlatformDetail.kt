package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class PlatformDetail(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("platform")
    val platform: Int,

    @field:SerializedName("slug")
    val slug: String
)