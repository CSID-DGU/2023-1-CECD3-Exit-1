package com.cecd.exitmed.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseTextSearchBookmarkedList(
    val data: List<Data>
) {
    @Serializable
    data class Data(
        val pillName: String,
        val pillItemSequence: Int
    )
}
