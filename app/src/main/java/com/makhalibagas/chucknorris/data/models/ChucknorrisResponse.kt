package com.makhalibagas.chucknorris.data.models

import com.google.gson.annotations.SerializedName
import com.makhalibagas.chucknorris.domain.domain.Chucknorris

data class ChucknorrisResponse(

    @field:SerializedName("icon_url")
    val iconUrl: String,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("categories")
    val categories: List<Any>,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("value")
    val value: String,

    @field:SerializedName("url")
    val url: String
) {
	fun toChucknorris() = Chucknorris(iconUrl, updatedAt, createdAt, categories, id, value, url)
}
