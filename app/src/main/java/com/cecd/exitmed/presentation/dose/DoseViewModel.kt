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
    private val _doseTimeTableState =
        MutableStateFlow<UiState<List<DoseTimeTable>>>(UiState.Loading)
    val doseTimeTableState get() = _doseTimeTableState.asStateFlow()

    init {
        fetchMyDrawerList()
        fetchDoseTimeTable()
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

    private fun fetchDoseTimeTable() {
        viewModelScope.launch {
            doseRepository.fetchDoseTimeTable()
                .onSuccess { doseTimeTable ->
                    _doseTimeTableState.value = UiState.Success(doseTimeTable)
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }
}
