package com.sideproject.leboncoinapp.core.di

import android.content.Context
import androidx.room.Room
import com.sideproject.data.album.local.dao.AlbumDao
import com.sideproject.data.album.remote.RemoteDataSourceImpl
import com.sideproject.data.album.remote.api.AlbumAPIService
import com.sideproject.data.album.remote.api.RequestInterceptor
import com.sideproject.data.album.repository.AlbumDataRepositoryImpl
import com.sideproject.data.album.usecase.CheckIfAlbumEmptyUseCaseImpl
import com.sideproject.data.album.usecase.FindAlbumUseCaseImpl
import com.sideproject.data.album.usecase.GetLocalAlbumsUseCaseImpl
import com.sideproject.data.album.usecase.GetRemoteAlbumsUseCaseImpl
import com.sideproject.data.album.usecase.SaveAlbumsUseCaseImpl
import com.sideproject.domain.repository.album.AlbumDataRepository
import com.sideproject.domain.source.RemoteDataSource
import com.sideproject.domain.usecase.CheckIfAlbumEmptyUseCase
import com.sideproject.domain.usecase.FindAlbumUseCase
import com.sideproject.domain.usecase.GetAlbumsUseCase
import com.sideproject.domain.usecase.SaveAlbumsUseCase
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
    fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource {
        return remoteDataSourceImpl
    }

    @Provides
    fun provideAlbumDataRepository(albumDataRepositoryImpl: AlbumDataRepositoryImpl): AlbumDataRepository {
        return albumDataRepositoryImpl
    }

    @Provides
    fun provideCheckIfAlbumEmptyUseCase(checkIfAlbumEmptyUseCaseImpl: CheckIfAlbumEmptyUseCaseImpl): CheckIfAlbumEmptyUseCase {
        return checkIfAlbumEmptyUseCaseImpl
    }

    @GetLocalAlbumsUseCase
    @Provides
    fun provideGetLocalAlbumsUseCaseImpl(getLocalAlbumsUseCaseImpl: GetLocalAlbumsUseCaseImpl): GetAlbumsUseCase {
        return getLocalAlbumsUseCaseImpl
    }

    @GetRemoteAlbumsUseCase
    @Provides
    fun provideGetRemoteAlbumsUseCase(getRemoteAlbumsUseCaseImpl: GetRemoteAlbumsUseCaseImpl): GetAlbumsUseCase {
        return getRemoteAlbumsUseCaseImpl
    }

    @Provides
    fun provideFindAlbumUseCase(findAlbumUseCaseImpl: FindAlbumUseCaseImpl): FindAlbumUseCase {
        return findAlbumUseCaseImpl
    }

    @Provides
    fun provideSaveAlbumsUseCase(saveAlbumsUseCaseImpl: SaveAlbumsUseCaseImpl): SaveAlbumsUseCase {
        return saveAlbumsUseCaseImpl
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
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): AlbumAPIService = retrofit.create(AlbumAPIService::class.java)
}
