package com.cecd.exitmed.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestEmailDoubleCheck(
    val userId: String
)
