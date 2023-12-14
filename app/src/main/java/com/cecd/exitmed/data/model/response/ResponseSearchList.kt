package com.cecd.exitmed.data.model.response

import com.cecd.exitmed.domain.type.Pill
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSearchList(
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
        Pill(
            pillItemSequence = searchPill.pillItemSequence,
            image = searchPill.image,
            pillName = searchPill.pillName,
            classification = searchPill.classification
        )
    }
}
