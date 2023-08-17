package com.cecd.exitmed.presentation.pillCreation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PillCreationViewModel : ViewModel() {
    private val _pagePosition = MutableStateFlow(0)
    val pagePosition get() = _pagePosition.asStateFlow()
    private val _lastDoseDate = MutableStateFlow<String?>(null)
    val lastDoseDate get() = _lastDoseDate.asStateFlow()
    private val _pillTimeList = MutableStateFlow<MutableList<String?>?>(null)
    val pillTimeList get() = _pillTimeList.asStateFlow()
    var pillMemo = MutableStateFlow<String?>(null)

    fun setLastDoseDate(date: String) {
        _lastDoseDate.value = date
    }

    fun setPillTimeList(pillTimeList: MutableList<String?>) {
        _pillTimeList.value = pillTimeList
    }

    fun setPagePosition(position: Int) {
        _pagePosition.value = position
    }
}
