package com.cecd.exitmed.domain.type

data class PillDrawerData(
    val isAlarmTurned: Boolean,
    val classification: String,
    val imageLink: String,
    val pillItemSequence: Int,
    val pillName: String
)
