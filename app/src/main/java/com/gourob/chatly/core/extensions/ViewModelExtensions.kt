package com.gourob.chatly.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gourob.chatly.core.ui.UiStateManager
import kotlinx.coroutines.launch

/**
 * Extension functions for ViewModels to easily interact with UiStateManager
 */

fun ViewModel.executeWithLoading(
    uiStateManager: UiStateManager,
    loadingMessage: String = "Loading...",
    onError: ((Throwable) -> String)? = null,
    onSuccess: ((Any?) -> String?)? = null,
    action: suspend () -> Any?
) {
    viewModelScope.launch {
        uiStateManager.showLoading(loadingMessage)
        try {
            val result = action()
            val successMessage = onSuccess?.invoke(result)
            if (successMessage != null) {
                uiStateManager.showSuccess(successMessage)
            } else {
                uiStateManager.hideLoading()
            }
        } catch (e: Exception) {
            val errorMessage = onError?.invoke(e) ?: e.localizedMessage ?: "An error occurred"
            uiStateManager.showError(errorMessage, e)
        }
    }
}

fun ViewModel.showError(
    uiStateManager: UiStateManager,
    message: String,
    throwable: Throwable? = null
) {
    uiStateManager.showError(message, throwable)
}

fun ViewModel.showSuccess(
    uiStateManager: UiStateManager,
    message: String
) {
    uiStateManager.showSuccess(message)
}

fun ViewModel.showLoading(
    uiStateManager: UiStateManager,
    message: String = "Loading..."
) {
    uiStateManager.showLoading(message)
}

fun ViewModel.hideLoading(
    uiStateManager: UiStateManager
) {
    uiStateManager.hideLoading()
}