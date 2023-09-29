package com.cecd.exitmed.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestBookmark(
    val pillItemSequence: Int
)
