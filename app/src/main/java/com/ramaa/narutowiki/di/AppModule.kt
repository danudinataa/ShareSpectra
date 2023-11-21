package com.ramaa.narutowiki.di

import android.app.Application
import com.ramaa.narutowiki.data.manager.LocalUserManagerImpl
import com.ramaa.narutowiki.data.remote.NarutoAPI
import com.ramaa.narutowiki.data.repository.CharacterRepositoryImpl
import com.ramaa.narutowiki.domain.manager.LocalUserManager
import com.ramaa.narutowiki.domain.repository.CharacterRepository
import com.ramaa.narutowiki.domain.usecases.characters.CharacterUseCases
import com.ramaa.narutowiki.domain.usecases.characters.GetCharacters
import com.ramaa.narutowiki.domain.usecases.characters.SearchCharacters
import com.ramaa.narutowiki.domain.usecases.entry.AppEntry
import com.ramaa.narutowiki.domain.usecases.entry.ReadAppEntry
import com.ramaa.narutowiki.domain.usecases.entry.SaveAppEntry
import com.ramaa.narutowiki.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideApiInstance(): NarutoAPI {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NarutoAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        narutoApi: NarutoAPI
    ): CharacterRepository {
        return CharacterRepositoryImpl(narutoApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        characterRepository: CharacterRepository
    ): CharacterUseCases {
        return CharacterUseCases(
            getCharacters = GetCharacters(characterRepository),
            searchCharacters = SearchCharacters(characterRepository)
        )
    }

}