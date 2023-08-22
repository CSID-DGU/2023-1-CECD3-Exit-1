package com.cecd.exitmed.presentation.pillDrawerDetail

import androidx.lifecycle.ViewModel
import com.cecd.exitmed.domain.type.DoseTime

class PillDrawerDetailViewModel : ViewModel() {
    val doseTimeMockList = listOf(
        DoseTime(
            1,
            "오전 10:00"
        ),
        DoseTime(
            2,
            "오후 6:00"
        )
    )
}
