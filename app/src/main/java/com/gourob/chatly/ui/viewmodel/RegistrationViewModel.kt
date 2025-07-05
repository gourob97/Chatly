package com.gourob.chatly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gourob.chatly.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegistrationUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val username: String = "",
    val password: String = ""
)

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updateUsername(username: String) {
        _uiState.value = _uiState.value.copy(username = username)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun register() {
        val currentState = _uiState.value
        
        // Basic validation
        if (currentState.email.isBlank() || currentState.username.isBlank() || currentState.password.isBlank()) {
            _uiState.value = currentState.copy(error = "All fields are required")
            return
        }

        if (currentState.password.length < 6) {
            _uiState.value = currentState.copy(error = "Password must be at least 6 characters")
            return
        }

        viewModelScope.launch {
            _uiState.value = currentState.copy(isLoading = true, error = null)
            
            authRepository.register(
                email = currentState.email,
                username = currentState.username,
                password = currentState.password
            ).fold(
                onSuccess = { response ->
                    if (response.success) {
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            isSuccess = true,
                            error = null
                        )
                    } else {
                        _uiState.value = currentState.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        error = exception.message ?: "Registration failed"
                    )
                }
            )
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun resetState() {
        _uiState.value = RegistrationUiState()
    }
} 