package com.begobang.presentation.di

import com.begobang.presentation.ui.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

/*
    Navigations are made through NavigationManager, so we need to instantiate just once and
    for that reason, when need to provide it once in this Module. NavigationManager will be
    called from ViewModel type of classes, so it needs to be injected. For Hilt to do it, we
    need to provide it.
 */
@Module
@InstallIn(SingletonComponent::class)
object PresentationModuleDI {
    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher