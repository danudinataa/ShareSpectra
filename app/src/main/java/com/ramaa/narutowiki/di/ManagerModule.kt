package com.ramaa.narutowiki.di

import com.ramaa.narutowiki.data.manager.LocalUserManagerImpl
import com.ramaa.narutowiki.domain.manager.LocalUserManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Binds
    @Singleton
    abstract fun bindLocalUserManager(localUserManagerImpl: LocalUserManagerImpl) : LocalUserManager
}