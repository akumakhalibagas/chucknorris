package com.makhalibagas.chucknorris.domain.domain

data class Chucknorris(
    val iconUrl: String,
    val updatedAt: String,
    val createdAt: String,
    val categories: List<Any>,
    val id: String,
    val value: String,
    val url: String
)
