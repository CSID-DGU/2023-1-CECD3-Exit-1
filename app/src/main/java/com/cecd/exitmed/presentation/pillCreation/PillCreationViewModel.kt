package com.cecd.exitmed.presentation.pillCreation

import androidx.lifecycle.ViewModel
import com.cecd.exitmed.type.DayType
import com.cecd.exitmed.type.PillPeriodType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PillCreationViewModel : ViewModel() {
    private val _pagePosition = MutableStateFlow(0)
    val pagePosition get() = _pagePosition.asStateFlow()
    private val _lastDoseDate = MutableStateFlow<String?>(null)
    val lastDoseDate get() = _lastDoseDate.asStateFlow()
    private val _pillPeriodType = MutableStateFlow<PillPeriodType?>(null)
    val pillPeriodType get() = _pillPeriodType.asStateFlow()
    val pillDayType = MutableStateFlow(
        mapOf(
            DayType.MONDAY to false,
            DayType.TUESDAY to false,
            DayType.WEDNESDAY to false,
            DayType.THURSDAY to false,
            DayType.FRIDAY to false,
            DayType.SATURDAY to false,
            DayType.SUNDAY to false
        )
    )
    private val _pillTimeList = MutableStateFlow<MutableList<String?>?>(null)
    val pillTimeList get() = _pillTimeList.asStateFlow()
    var pillMemo = MutableStateFlow<String?>(null)

    fun setLastDoseDate(date: String) {
        _lastDoseDate.value = date
    }

    fun setPillPeriod(periodType: PillPeriodType) {
        _pillPeriodType.value = periodType
    }

    fun setPillDay(pillDay: DayType) {
        val isSelected = pillDayType.value[pillDay] ?: return
        pillDayType.value = pillDayType.value.toMutableMap().apply {
            this[pillDay] = !isSelected
        }
    }

    fun setPillTimeList(pillTimeList: MutableList<String?>) {
        _pillTimeList.value = pillTimeList
    }

    fun setPagePosition(position: Int) {
        _pagePosition.value = position
    }
}
