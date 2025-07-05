package com.gourob.chatly.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val success: Boolean,
    val message: String,
    val user: User? = null
)

@Serializable
data class User(
    val id: String,
    val email: String,
    val username: String
) 