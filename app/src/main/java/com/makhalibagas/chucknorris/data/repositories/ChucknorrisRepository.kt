package com.makhalibagas.chucknorris.data.repositories

import com.makhalibagas.chucknorris.data.Resource
import com.makhalibagas.chucknorris.data.datasource.remote.ChucknorrisRemoteDataSource
import com.makhalibagas.chucknorris.data.datasource.network.ApiResponse
import com.makhalibagas.chucknorris.domain.domain.Chucknorris
import com.makhalibagas.chucknorris.domain.repository.IChucknorrisRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChucknorrisRepository @Inject constructor(
    private val remoteDataSource: ChucknorrisRemoteDataSource
) : IChucknorrisRepository {

    override fun getChucknorris(query: String): Flow<Resource<List<Chucknorris>>> = flow {
        when (val apiResource = remoteDataSource.getChucknorris(query).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResource.data.map { it.toChucknorris() }))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResource.msg))
            }
        }
    }

}