package com.gourob.chatly.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gourob.chatly.core.extensions.executeWithLoading
import com.gourob.chatly.core.ui.UiStateManager
import com.gourob.chatly.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

enum class AuthScreenType {
    LOGIN,
    SIGNUP
}

data class ChatlyLoginUiState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val isLoginSuccess: Boolean = false,
        val authScreenType: AuthScreenType = AuthScreenType.LOGIN
)

@HiltViewModel
class AuthenticationViewModel
@Inject
constructor(
        private val authRepository: AuthRepository,
        private val uiStateManager: UiStateManager
) : ViewModel() {

    val isAuthenticated: StateFlow<Boolean> =
            authRepository
                    .currentUser
                    .map { user ->
                        println("AuthViewModel - currentUser changed: $user")
                        val isAuth = user != null
                        println("AuthViewModel - isAuthenticated will be: $isAuth")
                        isAuth
                    }
                    .stateIn(
                            scope = viewModelScope,
                            started =
                                    SharingStarted
                                            .Eagerly, // Changed to Eagerly to start immediately
                            initialValue = authRepository.currentUser.value != null
                    )

    fun toggleAuthScreen() {
        uiState =
                uiState.copy(
                        authScreenType =
                                if (uiState.authScreenType == AuthScreenType.LOGIN)
                                        AuthScreenType.SIGNUP
                                else AuthScreenType.LOGIN
                )
    }

    var uiState by mutableStateOf(ChatlyLoginUiState())
        private set

    fun onEmailChange(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }

    fun login() {
        println("AuthViewModel - Login function called")
        executeWithLoading(
                uiStateManager = uiStateManager,
                loadingMessage =
                        if (uiState.authScreenType == AuthScreenType.LOGIN) "Signing in..."
                        else "Creating account...",
                onError = { throwable ->
                    println("AuthViewModel - Login error: ${throwable.localizedMessage}")
                    when {
                        throwable.localizedMessage?.contains("network", ignoreCase = true) ==
                                true -> "Network error. Please check your connection."
                        throwable.localizedMessage?.contains("password", ignoreCase = true) ==
                                true -> "Invalid email or password."
                        throwable.localizedMessage?.contains("email", ignoreCase = true) == true ->
                                "Please enter a valid email address."
                        else -> throwable.localizedMessage
                                        ?: "Authentication failed. Please try again."
                    }
                },
                onSuccess = {
                    println("AuthViewModel - Login success callback")
                    // Don't show success message immediately - let navigation happen first
                    null
                }
        ) {
            println("AuthViewModel - Executing login logic")
            // Clear any previous error state
            uiState = uiState.copy(errorMessage = null)

            val result = authRepository.login(uiState.email.trim(), uiState.password.trim())
            println("AuthViewModel - Login result: $result")

            if (result.isSuccess) {
                println("AuthViewModel - Login successful, setting isLoginSuccess = true")
                uiState = uiState.copy(isLoginSuccess = true)
                println("AuthViewModel - uiState.isLoginSuccess is now: ${uiState.isLoginSuccess}")
                result
            } else {
                // Update local state for any UI-specific error handling
                val errorMessage =
                        result.exceptionOrNull()?.localizedMessage ?: "Authentication failed"
                uiState = uiState.copy(errorMessage = errorMessage)
                throw Exception(errorMessage)
            }
        }
    }

    fun logout() {
        executeWithLoading(
                uiStateManager = uiStateManager,
                loadingMessage = "Signing out...",
                onSuccess = { "Signed out successfully" }
        ) { authRepository.logout() }
    }

    // Helper function for signup if you need it
    fun signup() {
        executeWithLoading(
                uiStateManager = uiStateManager,
                loadingMessage = "Creating account...",
                onError = { throwable ->
                    when {
                        throwable.localizedMessage?.contains("email", ignoreCase = true) == true ->
                                "Email is already in use or invalid."
                        throwable.localizedMessage?.contains("password", ignoreCase = true) ==
                                true -> "Password must be at least 6 characters long."
                        throwable.localizedMessage?.contains("network", ignoreCase = true) ==
                                true -> "Network error. Please check your connection."
                        else -> throwable.localizedMessage
                                        ?: "Failed to create account. Please try again."
                    }
                },
                onSuccess = {
                    // Don't show success message immediately - let navigation happen first
                    null
                }
        ) {
            // Clear any previous error state
            uiState = uiState.copy(errorMessage = null)

            // Use the proper signup method from AuthRepository
            val result = authRepository.signUp(uiState.email.trim(), uiState.password.trim())

            if (result.isSuccess) {
                uiState = uiState.copy(isLoginSuccess = true)
                result
            } else {
                val errorMessage =
                        result.exceptionOrNull()?.localizedMessage ?: "Failed to create account"
                uiState = uiState.copy(errorMessage = errorMessage)
                throw Exception(errorMessage)
            }
        }
    }

    // Utility function to clear errors manually if needed
    fun clearError() {
        uiState = uiState.copy(errorMessage = null)
    }

    // Utility function to show custom messages
    fun showMessage(message: String, isError: Boolean = false) {
        if (isError) {
            uiStateManager.showError(message)
        } else {
            uiStateManager.showInfo(message)
        }
    }

    // Reset login success state after navigation
    fun resetLoginSuccess() {
        uiState = ChatlyLoginUiState()
    }

    // Debug function to check current auth state
    fun checkAuthState() {
        println("AuthViewModel - Manual auth check:")
        println(
                "AuthViewModel - authRepository.currentUser.value: ${authRepository.currentUser.value}"
        )
        println("AuthViewModel - isAuthenticated.value: ${isAuthenticated.value}")
    }

    // Function to clear all UI states (useful for debugging)
    fun clearAllStates() {
        println("AuthViewModel - Clearing all UI states")
        uiStateManager.reset()
        uiState = uiState.copy(
            isLoading = false,
            errorMessage = null,
            isLoginSuccess = false
        )
    }
}
