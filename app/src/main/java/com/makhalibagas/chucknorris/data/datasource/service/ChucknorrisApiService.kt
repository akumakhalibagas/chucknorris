package com.makhalibagas.chucknorris.data.datasource.service

import com.makhalibagas.chucknorris.data.models.ChucknorrisResponse
import com.makhalibagas.chucknorris.data.models.ResWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ChucknorrisApiService {

    @GET("search")
    suspend fun getChucknorris(
        @Query("query") query: String
    ) : ResWrapper<List<ChucknorrisResponse>>
}