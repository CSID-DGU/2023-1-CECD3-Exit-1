package com.cecd.exitmed.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseRecentSearchTerm(
    val data: List<Data>
) {
    @Serializable
    data class Data(
        val searchText: String
    )

    fun toStringList() = data.map { searchText ->
        searchText.searchText
    }
}
