package com.rivaldofez.vogames.core.data.source.remote.response.subtype

import com.google.gson.annotations.SerializedName

data class StoresItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("store")
    val store: Store
)