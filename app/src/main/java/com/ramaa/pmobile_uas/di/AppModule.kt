package com.ramaa.pmobile_uas.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.ramaa.pmobile_uas.data.local.StocksDao
import com.ramaa.pmobile_uas.data.local.StocksDatabase
import com.ramaa.pmobile_uas.data.remote.StockAPI
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
    fun provideApiInstance(): StockAPI {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideStocksDatabase(
        application: Application
    ): StocksDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = StocksDatabase::class.java,
            name = DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideStocksDao(
        stocksDatabase: StocksDatabase
    ): StocksDao = stocksDatabase.stocksDao

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