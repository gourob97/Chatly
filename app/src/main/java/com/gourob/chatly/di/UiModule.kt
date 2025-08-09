package com.gourob.chatly.di

import com.gourob.chatly.core.ui.UiStateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UiModule {
    
    @Provides
    @Singleton
    fun provideUiStateManager(): UiStateManager {
        return UiStateManager()
    }
}