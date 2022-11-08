package com.makhalibagas.chucknorris.presentation

import androidx.lifecycle.ViewModel
import com.makhalibagas.chucknorris.data.Resource
import com.makhalibagas.chucknorris.domain.domain.Chucknorris
import com.makhalibagas.chucknorris.domain.usecase.ChucknorrisUseCase
import com.makhalibagas.chucknorris.utils.UiStateWrapper
import com.makhalibagas.chucknorris.utils.collectLifecycleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: ChucknorrisUseCase
) : ViewModel() {

    private val _chucknorrisState = MutableSharedFlow<UiStateWrapper<List<Chucknorris>>>()
    val chucknorris = _chucknorrisState.asSharedFlow()

    private val _query = MutableStateFlow("")
    private val query = _query.asStateFlow()

    fun setQuery(query: String) {
        _query.value = query
    }

    fun getChucknorris() {
        collectLifecycleFlow(useCase(query.value)) { resource ->
            when (resource) {
                is Resource.Loading -> _chucknorrisState.emit(UiStateWrapper.Loading(true))
                is Resource.Success -> {
                    _chucknorrisState.emit(UiStateWrapper.Loading(false))
                    _chucknorrisState.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _chucknorrisState.emit(UiStateWrapper.Loading(false))
                    _chucknorrisState.emit(UiStateWrapper.Error(resource.msg))
                }
            }
        }
    }
}