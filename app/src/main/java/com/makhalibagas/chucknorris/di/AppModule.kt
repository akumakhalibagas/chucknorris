package com.makhalibagas.chucknorris.di

import com.makhalibagas.chucknorris.data.repositories.ChucknorrisRepository
import com.makhalibagas.chucknorris.domain.usecase.ChucknorrisUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChucknorrisUseCase(repository: ChucknorrisRepository) =
        ChucknorrisUseCase(repository)

}