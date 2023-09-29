package com.cecd.exitmed.data.model.response

import com.cecd.exitmed.domain.type.SearchPill
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTextSearchList(
    val data: List<Data>
) {
    @Serializable
    data class Data(
        val classification: String,
        val image: String,
        val pillItemSequence: Int,
        val pillName: String
    )

    fun toSearchPill() = data.map { searchPill ->
        SearchPill(
            pillItemSequence = searchPill.pillItemSequence,
            image = searchPill.image,
            pillName = searchPill.pillName,
            classification = searchPill.classification
        )
    }
}
