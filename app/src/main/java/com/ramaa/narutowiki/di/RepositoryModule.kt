package com.ramaa.narutowiki.di

import com.ramaa.narutowiki.data.repository.CharacterRepositoryImpl
import com.ramaa.narutowiki.domain.repository.CharacterRepository
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
    abstract fun bindNewsRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

}