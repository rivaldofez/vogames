package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class PlatformsItem(

    @field:SerializedName("requirements_ru")
    val requirementsRu: RequirementsRu,

    @field:SerializedName("requirements_en")
    val requirementsEn: RequirementsEn,

    @field:SerializedName("released_at")
    val releasedAt: String,

    @field:SerializedName("platform")
    val platform: Platform
)