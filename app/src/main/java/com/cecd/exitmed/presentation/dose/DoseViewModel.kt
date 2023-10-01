package com.cecd.exitmed.presentation.dose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.domain.repository.DoseRepository
import com.cecd.exitmed.domain.type.DoseTimeTable
import com.cecd.exitmed.domain.type.PillDrawerData
import com.cecd.exitmed.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DoseViewModel @Inject constructor(
    private val doseRepository: DoseRepository
) : ViewModel() {
    private val _myDrawerListState =
        MutableStateFlow<UiState<List<PillDrawerData>>>(UiState.Loading)
    val myDrawerListState get() = _myDrawerListState.asStateFlow()

    init {
        fetchMyDrawerList()
    }

    private fun fetchMyDrawerList() {
        viewModelScope.launch {
            doseRepository.fetchPillDrawerList()
                .onSuccess { myDrawerList ->
                    _myDrawerListState.value = UiState.Success(myDrawerList)
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

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
