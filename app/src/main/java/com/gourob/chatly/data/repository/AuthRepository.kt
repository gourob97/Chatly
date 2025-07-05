package com.gourob.chatly.data.repository

import com.gourob.chatly.data.model.RegistrationRequest
import com.gourob.chatly.data.model.RegistrationResponse
import com.gourob.chatly.data.remote.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun register(
        email: String,
        username: String,
        password: String
    ): Result<RegistrationResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = RegistrationRequest(email, username, password)
                val response = authApi.register(request)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
} 