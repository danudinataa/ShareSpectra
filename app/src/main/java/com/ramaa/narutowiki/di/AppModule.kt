package com.ramaa.narutowiki.di

import android.app.Application
import androidx.room.Room
import com.ramaa.narutowiki.data.local.CharacterConverter
import com.ramaa.narutowiki.data.local.CharactersDao
import com.ramaa.narutowiki.data.local.CharactersDatabase
import com.ramaa.narutowiki.data.remote.NarutoAPI
import com.ramaa.narutowiki.util.Constants.BASE_URL
import com.ramaa.narutowiki.util.Constants.DATABASE_NAME
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
    fun provideCharactersDatabase(
        application: Application
    ): CharactersDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = CharactersDatabase::class.java,
            name = DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(
        charactersDatabase: CharactersDatabase
    ): CharactersDao = charactersDatabase.charactersDao
}