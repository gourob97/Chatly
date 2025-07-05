package com.gourob.chatly.data.remote

import com.gourob.chatly.data.model.RegistrationRequest
import com.gourob.chatly.data.model.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun register(@Body request: RegistrationRequest): RegistrationResponse
} 