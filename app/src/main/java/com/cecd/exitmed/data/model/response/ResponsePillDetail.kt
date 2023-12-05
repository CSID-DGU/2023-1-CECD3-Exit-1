package com.cecd.exitmed.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponsePillDetail(
    val age: Int,
    val classification: String,
    val dosage: String,
    val effect: String,
    val favorite: Boolean,
    val imageLink: String,
    val ingredient: String,
    val pillName: String,
    val pregnant: Boolean,
    val storage: String,
    val warning: String,
    val duplicatedPills: List<String>
)
