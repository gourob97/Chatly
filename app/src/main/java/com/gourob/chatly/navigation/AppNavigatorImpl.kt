package com.gourob.chatly.navigation

import android.util.Log
import androidx.navigation.NavHostController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton


class AppNavigatorImpl @Inject() constructor() : AppNavigator {

    private var navController: NavHostController? = null

    override fun setNavController(controller: NavHostController) {
        Log.d("AppNavigatorImpl", "Setting NavController: $controller")
        navController = controller
    }

    override fun <T : Any> navigateTo(
        route: T,
        popUpTo: T?,
        inclusive: Boolean
    ) {
        navController?.navigate(route) {
            if (popUpTo != null) {
                val popRoute = popUpTo::class.simpleName ?: return@navigate
                popUpTo(popRoute) { this.inclusive = inclusive }
            }
        }

//        navController?.navigate(route) {
//            if (popUpTo != null) {
//                // Handle popUpTo if needed
//                Log.d("AppNavigatorImpl", "PopUpTo requested")
//            }
//        }
    }

    override fun goBack() {
        Log.d("AppNavigatorImpl", "Going back, NavController: $navController")
        navController?.popBackStack()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NavigatorModule {
    @Provides
    @Singleton
    fun provideAppNavigatorImpl(): AppNavigator {
        return AppNavigatorImpl()
    }
}