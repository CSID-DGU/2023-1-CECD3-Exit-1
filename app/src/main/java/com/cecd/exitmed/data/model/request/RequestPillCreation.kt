package com.cecd.exitmed.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestPillCreation(
    val comment: String,
    val dayForDrawer: DayForDrawer,
    val dosageCycle: String,
    val finalDate: String,
    val pillItemSequence: Int,
    val takeTime: String
) {
    @Serializable
    data class DayForDrawer(
        val friday: Boolean,
        val monday: Boolean,
        val saturday: Boolean,
        val sunday: Boolean,
        val thursday: Boolean,
        val tuesday: Boolean,
        val wednesday: Boolean
    )
}
