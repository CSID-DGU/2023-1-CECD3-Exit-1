package com.cecd.exitmed.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUp(
    val userId: String,
    val fullName: String
)
