package com.makhalibagas.chucknorris.data.datasource.remote

import android.content.Context
import com.makhalibagas.chucknorris.R
import com.makhalibagas.chucknorris.data.datasource.network.ApiResponse
import com.makhalibagas.chucknorris.data.datasource.service.ChucknorrisApiService
import com.makhalibagas.chucknorris.data.models.ChucknorrisResponse
import com.makhalibagas.chucknorris.utils.isNetworkAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChucknorrisRemoteDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ChucknorrisApiService
) {

    fun getChucknorris(query: String): Flow<ApiResponse<List<ChucknorrisResponse>>> =
        flow {
            if (!context.isNetworkAvailable()) {
                emit(ApiResponse.Error(context.getString(R.string.text_error_network_not_avail)))
                return@flow
            }
            try {
                val response = apiService.getChucknorris(query)
                emit(ApiResponse.Success(response.result))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}