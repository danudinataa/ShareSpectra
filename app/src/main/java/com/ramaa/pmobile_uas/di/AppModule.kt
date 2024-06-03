package com.ramaa.pmobile_uas.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.ramaa.pmobile_uas.data.local.CharactersDao
import com.ramaa.pmobile_uas.data.local.CharactersDatabase
import com.ramaa.pmobile_uas.data.remote.NarutoAPI
import com.ramaa.pmobile_uas.presentation.login.GoogleAuthUiClient
import com.ramaa.pmobile_uas.util.Constants.BASE_URL
import com.ramaa.pmobile_uas.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(
        @ApplicationContext context: Context,
        oneTapClient: SignInClient
    ): GoogleAuthUiClient {
        return GoogleAuthUiClient(context, oneTapClient)
    }

    @Provides
    fun provideOneTapClient(
        @ApplicationContext context: Context
    ): SignInClient {
        return Identity.getSignInClient(context)
    }
}