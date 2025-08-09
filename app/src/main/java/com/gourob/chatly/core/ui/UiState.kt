package com.gourob.chatly.core.ui

sealed class UiState {
    data object Idle : UiState()
    data class Loading(val message: String = "Loading...") : UiState()
    data class Error(val message: String, val throwable: Throwable? = null) : UiState()
    data class Success(val message: String? = null) : UiState()
}

data class SnackbarMessage(
    val message: String,
    val type: SnackbarType = SnackbarType.INFO,
    val duration: SnackbarDuration = SnackbarDuration.SHORT,
    val actionLabel: String? = null,
    val onAction: (() -> Unit)? = null
)

enum class SnackbarType {
    SUCCESS, ERROR, INFO, WARNING
}

enum class SnackbarDuration {
    SHORT, LONG, INDEFINITE
}