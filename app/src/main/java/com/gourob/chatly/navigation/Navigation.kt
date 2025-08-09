package com.gourob.chatly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gourob.chatly.feature.auth.AuthScreen
import com.gourob.chatly.feature.auth.AuthScreenType
import com.gourob.chatly.feature.auth.AuthenticationViewModel
import com.gourob.chatly.feature.home.HomeScreen
import com.gourob.chatly.feature.splash.SplashScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val authViewModel: AuthenticationViewModel = hiltViewModel()
    
    // Observe authentication state
    val isAuthenticated by authViewModel.isAuthenticated.collectAsStateWithLifecycle()
    
    // Determine start destination based on authentication state
    val startDestination = if (isAuthenticated) HomeScreen else SplashScreen

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<SplashScreen> {
            SplashScreen(
                onSplashFinished = {
                    // After splash, navigate to login (splash only shows for unauthenticated users)
                    navController.navigate(LoginScreen) {
                        popUpTo(SplashScreen) { inclusive = true }
                    }
                }
            )
        }
        
        composable<LoginScreen> {
            AuthScreen(viewModel = authViewModel)
            
            // Handle authentication success from login screen
            LaunchedEffect(isAuthenticated) {
                if (isAuthenticated) {
                    navController.navigate(HomeScreen) {
                        popUpTo(LoginScreen) { inclusive = true }
                    }
                    
                    // Show welcome message
                    if (authViewModel.uiState.isLoginSuccess) {
                        val message = if (authViewModel.uiState.authScreenType == AuthScreenType.LOGIN) {
                            "Welcome back!"
                        } else {
                            "Account created successfully! Welcome!"
                        }
                        authViewModel.showMessage(message, isError = false)
                        authViewModel.resetLoginSuccess()
                    }
                }
            }
        }

        composable<HomeScreen> {
            HomeScreen(viewModel = authViewModel)
            
            // Handle logout from home screen
            LaunchedEffect(isAuthenticated) {
                if (!isAuthenticated) {
                    navController.navigate(SplashScreen) {
                        popUpTo(HomeScreen) { inclusive = true }
                    }
                }
            }
        }
    }
}
