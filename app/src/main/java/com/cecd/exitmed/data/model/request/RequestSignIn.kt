package com.cecd.exitmed.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignIn(
    val userId: String,
    val userPassword: String
)
