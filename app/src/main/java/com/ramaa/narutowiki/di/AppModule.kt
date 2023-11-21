package com.ramaa.narutowiki.di

import android.app.Application
import com.ramaa.narutowiki.data.manager.LocalUserManagerImpl
import com.ramaa.narutowiki.domain.manager.LocalUserManager
import com.ramaa.narutowiki.domain.usecases.entry.AppEntry
import com.ramaa.narutowiki.domain.usecases.entry.ReadAppEntry
import com.ramaa.narutowiki.domain.usecases.entry.SaveAppEntry
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
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntry = AppEntry(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

}