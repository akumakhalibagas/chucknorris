package com.makhalibagas.chucknorris.domain.repository

import com.makhalibagas.chucknorris.data.Resource
import com.makhalibagas.chucknorris.domain.domain.Chucknorris
import kotlinx.coroutines.flow.Flow

interface IChucknorrisRepository {
    fun getChucknorris(query: String): Flow<Resource<List<Chucknorris>>>
}