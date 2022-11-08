package com.makhalibagas.chucknorris.domain.usecase

import com.makhalibagas.chucknorris.data.Resource
import com.makhalibagas.chucknorris.domain.domain.Chucknorris
import com.makhalibagas.chucknorris.domain.repository.IChucknorrisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChucknorrisUseCase @Inject constructor(
    private val repository: IChucknorrisRepository
) {

    operator fun invoke(query: String): Flow<Resource<List<Chucknorris>>> {
        return repository.getChucknorris(query)
    }

}