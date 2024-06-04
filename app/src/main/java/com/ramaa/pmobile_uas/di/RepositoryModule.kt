package com.ramaa.pmobile_uas.di

import com.ramaa.pmobile_uas.data.repository.StockRepositoryImpl
import com.ramaa.pmobile_uas.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(stockRepositoryImpl: StockRepositoryImpl): StockRepository

}