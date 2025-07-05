package com.gourob.chatly.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gourob.chatly.LoginScreen
import com.gourob.chatly.RegistrationScreen
import com.gourob.chatly.ui.viewmodel.RegistrationViewModel
import dagger.hilt.android.EntryPointAccessors
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    Log.d("Navigation", "NavController created: $navController")

    // Get the AppNavigatorImpl from Hilt and set the NavController
    LaunchedEffect(navController) {
        try {
            Log.d("Navigation", "LaunchedEffect triggered, setting up AppNavigator")
            val context = navController.context
            Log.d("Navigation", "Context: $context")
            
            val entryPoint = EntryPointAccessors.fromApplication(
                context.applicationContext,
                NavigatorEntryPoint::class.java
            )
            Log.d("Navigation", "EntryPoint created: $entryPoint")
            
            val appNavigator = entryPoint.appNavigator()
            Log.d("Navigation", "AppNavigator retrieved: $appNavigator")
            
            appNavigator.setNavController(navController)
            Log.d("Navigation", "NavController set successfully")
        } catch (e: Exception) {
            Log.e("Navigation", "Error setting up AppNavigator", e)
        }
    }

    NavHost(
        navController = navController,
        startDestination = RegistrationRoute,
        modifier = modifier
    ) {
        composable<RegistrationRoute>{
            val viewModel: RegistrationViewModel = hiltViewModel()
            RegistrationScreen(
                viewModel = viewModel
            )
        }

        composable<LoginRoute> {
            LoginScreen()
        }
    }
}

@Serializable
object RegistrationRoute

@Serializable
object LoginRoute