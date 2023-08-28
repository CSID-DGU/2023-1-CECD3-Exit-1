package com.cecd.exitmed.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignIn(
    @SerialName("Authorization")
    val refreshToken: String
)
