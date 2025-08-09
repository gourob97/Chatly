package com.gourob.chatly.data.repository.auth

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<FirebaseUser>
    suspend fun signUp(email: String, password: String): Result<FirebaseUser>
    fun logout()
    val currentUser: StateFlow<FirebaseUser?>
}