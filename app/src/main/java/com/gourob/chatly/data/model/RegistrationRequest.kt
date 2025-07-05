package com.gourob.chatly.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val email: String,
    val username: String,
    val password: String
) 