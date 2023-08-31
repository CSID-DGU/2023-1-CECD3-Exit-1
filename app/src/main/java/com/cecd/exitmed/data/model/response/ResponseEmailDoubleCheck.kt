package com.cecd.exitmed.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseEmailDoubleCheck(
    val duplicated: Boolean
)
