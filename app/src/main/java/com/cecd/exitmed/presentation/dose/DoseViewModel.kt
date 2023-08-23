package com.cecd.exitmed.presentation.dose

import androidx.lifecycle.ViewModel
import com.cecd.exitmed.domain.type.DoseTimeTable
import com.cecd.exitmed.domain.type.DrawerPill

class DoseViewModel : ViewModel() {
    val mockPillDrawerList = listOf(
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            false
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            false
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
        DrawerPill(
            1,
            "타이레놀",
            "진통제",
            true
        ),
    )

    val mockDoseTimeTable = listOf(
        DoseTimeTable(
            1,
            "오전 10:00",
            "타이레놀",
            true
        ),
        DoseTimeTable(
            1,
            "오후 12:00",
            "타이레놀",
            true
        ),
        DoseTimeTable(
            1,
            "오후 12:00",
            "타이레놀",
            true
        ),
        DoseTimeTable(
            1,
            "오후 6:00",
            "타이레놀",
            false
        ),
        DoseTimeTable(
            1,
            "오전 6:00",
            "타이레놀",
            false
        )
    )
}
