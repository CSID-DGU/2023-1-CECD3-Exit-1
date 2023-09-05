package com.cecd.exitmed.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUp(
    val userId: String,
    val userPassword: String,
    val dateOfBirth: Int,
    val fullName: String,
    val sex: String?,
    val isPregnant: Boolean
)
