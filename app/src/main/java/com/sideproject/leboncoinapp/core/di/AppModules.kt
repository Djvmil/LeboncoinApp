package com.sideproject.leboncoinapp.core.di

import android.content.Context
import androidx.room.Room
import com.sideproject.data.album.local.dao.AlbumDao
import com.sideproject.data.album.remote.api.AlbumAPIService
import com.sideproject.data.album.repository.AlbumLocalDataRepositoryImpl
import com.sideproject.data.album.repository.AlbumRemoteDataRepositoryImpl
import com.sideproject.domain.repository.album.AlbumLocalRepository
import com.sideproject.domain.repository.album.AlbumRemoteRepository
import com.sideproject.leboncoinapp.core.constants.Constants
import com.sideproject.leboncoinapp.database.AlbumCallBack
import com.sideproject.leboncoinapp.database.LeboncoinAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Singleton
    @Provides
    fun provideLeboncoinDatabase(
        @ApplicationContext context: Context,
        provider: Provider<AlbumDao>,
    ): LeboncoinAppDatabase {
        return Room.databaseBuilder(
            context,
            LeboncoinAppDatabase::class.java,
            Constants.DATABASE_NAME,
        )
            .addCallback(AlbumCallBack(provider))
            .allowMainThreadQueries().build()
    }

    @Provides
    fun provideAlbumDao(database: LeboncoinAppDatabase): AlbumDao {
        return database.albumDao()
    }

    @Provides
    fun provideAlbumLocalDataRepository(albumLocalDataRepositoryImpl: AlbumLocalDataRepositoryImpl): AlbumLocalRepository {
        return albumLocalDataRepositoryImpl
    }

    @Provides
    fun provideAlbumRemoteDataRepository(albumRemoteDataRepositoryImpl: AlbumRemoteDataRepositoryImpl): AlbumRemoteRepository {
        return albumRemoteDataRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(com.sideproject.data.constants.Constants.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): AlbumAPIService = retrofit.create(AlbumAPIService::class.java)
}
