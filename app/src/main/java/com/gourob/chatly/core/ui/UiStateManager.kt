package com.gourob.chatly.core.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiStateManager @Inject constructor() {
    
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    private val _snackbarMessage = MutableStateFlow<SnackbarMessage?>(null)
    val snackbarMessage: StateFlow<SnackbarMessage?> = _snackbarMessage.asStateFlow()
    
    fun showLoading(message: String = "Loading...") {
        _uiState.value = UiState.Loading(message)
    }
    
    fun showError(message: String, throwable: Throwable? = null) {
        _uiState.value = UiState.Error(message, throwable)
        _snackbarMessage.value = SnackbarMessage(
            message = message,
            type = SnackbarType.ERROR
        )
    }
    
    fun showSuccess(message: String? = null) {
        _uiState.value = UiState.Success(message)
        message?.let {
            _snackbarMessage.value = SnackbarMessage(
                message = it,
                type = SnackbarType.SUCCESS
            )
        }
    }
    
    fun showInfo(message: String) {
        _snackbarMessage.value = SnackbarMessage(
            message = message,
            type = SnackbarType.INFO
        )
    }
    
    fun showWarning(message: String) {
        _snackbarMessage.value = SnackbarMessage(
            message = message,
            type = SnackbarType.WARNING
        )
    }
    
    fun hideLoading() {
        _uiState.value = UiState.Idle
    }
    
    fun clearSnackbar() {
        _snackbarMessage.value = null
    }
    
    fun reset() {
        _uiState.value = UiState.Idle
        _snackbarMessage.value = null
    }
}