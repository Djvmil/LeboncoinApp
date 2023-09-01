package com.sideproject.leboncoinapp.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetRemoteAlbumsUseCase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetLocalAlbumsUseCase
