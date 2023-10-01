package com.cecd.exitmed.data.model.response

import com.cecd.exitmed.domain.type.PillDrawerData
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePillDrawerList(
    val data: List<Data>
) {
    @Serializable
    data class Data(
        val alarmTurnedOn: Boolean,
        val classification: String,
        val imageLink: String,
        val pillItemSequence: Int,
        val pillName: String
    )

    fun toPillDrawerData() = data.map { pillDrawer ->
        PillDrawerData(
            isAlarmTurned = pillDrawer.alarmTurnedOn,
            classification = pillDrawer.classification,
            imageLink = pillDrawer.imageLink,
            pillItemSequence = pillDrawer.pillItemSequence,
            pillName = pillDrawer.pillName
        )
    }
}
