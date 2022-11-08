package com.makhalibagas.chucknorris.data.models

import com.google.gson.annotations.SerializedName

data class ResWrapper<T>(

    @field:SerializedName("result")
    val result: T,

    @field:SerializedName("total")
    val total: Int
)