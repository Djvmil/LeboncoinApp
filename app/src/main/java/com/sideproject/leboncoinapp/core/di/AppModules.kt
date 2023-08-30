package com.sideproject.leboncoinapp.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.sideproject.data.album.local.dao.AlbumDao
import com.sideproject.data.album.repository.AlbumLocalDataRepositoryImpl
import com.sideproject.domain.repository.album.AlbumLocalRepository
import com.sideproject.domain.repository.album.AlbumRemoteRepository
import com.sideproject.leboncoinapp.core.constants.Constants
import com.sideproject.leboncoinapp.core.shareprefs.LeboncoinSharedPrefs
import com.sideproject.leboncoinapp.core.shareprefs.LeboncoinSharedPrefsImpl
import com.sideproject.leboncoinapp.database.AlbumCallBack
import com.sideproject.leboncoinapp.database.LeboncoinAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideAlbumRemoteDataRepository(albumRemoteRepository: AlbumRemoteRepository): AlbumRemoteRepository {
        return albumRemoteRepository
    }

    @Provides
    fun provideSharedPrefs(application: Application): SharedPreferences {
        return application.getSharedPreferences(Constants.SHARE_PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideLeboncoinSharePrefs(sharedPreferences: SharedPreferences): LeboncoinSharedPrefs {
        return LeboncoinSharedPrefsImpl(sharedPreferences)
    }
}
