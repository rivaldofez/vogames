package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class YearsItem(

    @field:SerializedName("filter")
    val filter: String,

    @field:SerializedName("nofollow")
    val nofollow: Boolean,

    @field:SerializedName("decade")
    val decade: Int,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("from")
    val from: Int,

    @field:SerializedName("to")
    val to: Int,

    @field:SerializedName("years")
    val years: List<YearsItem>,

    @field:SerializedName("year")
    val year: Int
)