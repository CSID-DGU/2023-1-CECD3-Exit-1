package com.cecd.exitmed.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseOnOffDoseAlarm(
    val on: Boolean,
    val pillItemSequence: Int
)
