package com.cecd.exitmed.presentation.pillCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.data.model.request.RequestPillCreation
import com.cecd.exitmed.domain.repository.DoseRepository
import com.cecd.exitmed.type.DayType
import com.cecd.exitmed.type.PillPeriodType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PillCreationViewModel @Inject constructor(
    private val doseRepository: DoseRepository
) : ViewModel() {
    private val _pagePosition = MutableStateFlow(0)
    val pagePosition get() = _pagePosition.asStateFlow()
    private val _lastDoseDate = MutableStateFlow("")
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
    private val _pillTimeList = MutableStateFlow<MutableList<String>>(mutableListOf())
    val pillTimeList get() = _pillTimeList.asStateFlow()
    var pillMemo = MutableStateFlow("")
    private val _isAdded = MutableStateFlow(false)
    val isAdded get() = _isAdded.asStateFlow()

    fun addToPillDrawer(itemSequence: Int) {
        viewModelScope.launch {
            doseRepository.addToPillDrawer(
                RequestPillCreation(
                    pillItemSequence = itemSequence,
                    finalDate = _lastDoseDate.value,
                    dosageCycle = _pillPeriodType.value?.name ?: "",
                    comment = pillMemo.value,
                    takeTime = pillTimeList.value.toMutableList(),
                    dayForDrawer = RequestPillCreation.DayForDrawer(
                        monday = pillDayType.value.get(DayType.MONDAY) == true,
                        tuesday = pillDayType.value.get(DayType.TUESDAY) == true,
                        wednesday = pillDayType.value.get(DayType.WEDNESDAY) == true,
                        thursday = pillDayType.value.get(DayType.THURSDAY) == true,
                        friday = pillDayType.value.get(DayType.FRIDAY) == true,
                        saturday = pillDayType.value.get(DayType.SATURDAY) == true,
                        sunday = pillDayType.value.get(DayType.SUNDAY) == true,
                    )
                )
            )
                .onSuccess {
                    _isAdded.value = it
                }
                .onFailure { throwable ->
                    Timber.d(throwable.message)
                }
        }
    }

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

    fun setPagePosition(position: Int) {
        _pagePosition.value = position
    }
}
