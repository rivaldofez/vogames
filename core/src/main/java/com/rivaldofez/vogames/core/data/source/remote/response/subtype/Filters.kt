package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class Filters(

    @field:SerializedName("years")
    val years: List<YearsItem>
)