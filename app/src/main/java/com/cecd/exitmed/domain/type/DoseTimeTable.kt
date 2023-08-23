package com.cecd.exitmed.domain.type

data class DoseTimeTable(
    val pillId: Int,
    val time: String,
    val pillName: String,
    val isPassed: Boolean
)
