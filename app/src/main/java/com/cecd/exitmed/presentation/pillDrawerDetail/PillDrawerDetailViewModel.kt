package com.cecd.exitmed.presentation.pillDrawerDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.domain.repository.DoseRepository
import com.cecd.exitmed.domain.type.DoseTime
import com.cecd.exitmed.domain.type.DrawerDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PillDrawerDetailViewModel @Inject constructor(
    private val doseRepository: DoseRepository
) : ViewModel() {
    private val _drawerPillDetail =
        MutableStateFlow<DrawerDetail?>(null)
    val drawerPillDetail get() = _drawerPillDetail.asStateFlow()

    fun fetchDrawerPillDetail(itemSeq: Int) {
        viewModelScope.launch {
            doseRepository.fetchDrawerPillDetail(itemSeq)
                .onSuccess { drawerPillDetail ->
                    _drawerPillDetail.value = drawerPillDetail
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

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
