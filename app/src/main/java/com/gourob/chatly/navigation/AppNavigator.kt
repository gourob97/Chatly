package com.gourob.chatly.navigation

import androidx.navigation.NavHostController
import kotlinx.serialization.KSerializer

interface AppNavigator {
    fun <T : Any> navigateTo(
        route: T,
        popUpTo: T? = null,
        inclusive: Boolean = false
    )
    fun goBack()
    fun setNavController(controller: NavHostController)
}


//@EntryPoint
//@InstallIn(SingletonComponent::class)
//interface NavigatorEntryPoint {
//    fun appNavigatorImpl(): AppNavigatorImpl
//}