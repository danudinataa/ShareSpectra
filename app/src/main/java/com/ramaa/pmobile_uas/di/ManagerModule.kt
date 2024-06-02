package com.ramaa.pmobile_uas.di

import com.ramaa.pmobile_uas.data.manager.LocalUserManagerImpl
import com.ramaa.pmobile_uas.domain.manager.LocalUserManager
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