package com.cecd.exitmed.data.model.response

import com.cecd.exitmed.domain.type.DrawerDetail
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDrawerDetail(
    val alarm: List<String>,
    val comment: String,
    val dosage: String,
    val finalDate: String,
    val imageLink: String,
    val pillName: String
)

fun ResponseDrawerDetail.toDrawerDetail(): DrawerDetail {
    return DrawerDetail(
        this.alarm,
        this.comment,
        this.dosage,
        this.finalDate,
        this.imageLink,
        this.pillName
    )
}
