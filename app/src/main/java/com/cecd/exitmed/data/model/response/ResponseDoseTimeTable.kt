package com.cecd.exitmed.data.model.response

import com.cecd.exitmed.domain.type.DoseTimeTable
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDoseTimeTable(
    val data: List<Data>
) {
    @Serializable
    data class Data(
        val passed: Boolean,
        val pillName: String,
        val takeTime: String
    )

    fun toDoseTimeTable() = data.map { doseTime ->
        DoseTimeTable(
            pillName = doseTime.pillName,
            time = doseTime.takeTime,
            isPassed = doseTime.passed
        )
    }
}
