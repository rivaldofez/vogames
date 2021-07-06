package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class ParentPlatformsItem(

    @field:SerializedName("platform")
    val platform: Platform
)