package com.gourob.chatly.navigation

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NavigatorEntryPoint {
    fun appNavigator(): AppNavigator
} 