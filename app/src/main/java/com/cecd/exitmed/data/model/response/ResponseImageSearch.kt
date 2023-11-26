package com.cecd.exitmed.data.model.response

import com.cecd.exitmed.presentation.model.ImageSearchModel
import kotlinx.serialization.Serializable

@Serializable
data class ResponseImageSearch(
    val imageLink: String,
    val pillItemSequence: Int,
    val pillName: String,
    val shape: String
)

fun ResponseImageSearch.toParcelableImageSearch(): ImageSearchModel {
    return ImageSearchModel(
        imageLink,
        pillItemSequence,
        pillName,
        shape
    )
}
