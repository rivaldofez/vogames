package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class RequirementsRu(

    @field:SerializedName("minimum")
    val minimum: String,

    @field:SerializedName("recommended")
    val recommended: String
)