package com.cecd.exitmed.data.model.response

import com.cecd.exitmed.domain.type.ImageSearchInfo
import kotlinx.serialization.Serializable

@Serializable
data class ResponseImageSearch(
    val data: List<Data>
) {
    @Serializable
    data class Data(
        val imageLink: String,
        val pillItemSequence: Int,
        val pillName: String,
        val shape: String
    )

    fun toImageSearchInfo() = data.map { imageInfo ->
        ImageSearchInfo(
            pillItemSequence = imageInfo.pillItemSequence,
            imageLink = imageInfo.imageLink,
            pillName = imageInfo.pillName,
            shape = imageInfo.shape
        )
    }
}
