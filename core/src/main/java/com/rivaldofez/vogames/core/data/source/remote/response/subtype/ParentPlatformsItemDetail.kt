package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class ParentPlatformsItemDetail(

    @field:SerializedName("platform")
    val platform: PlatformDetail
)