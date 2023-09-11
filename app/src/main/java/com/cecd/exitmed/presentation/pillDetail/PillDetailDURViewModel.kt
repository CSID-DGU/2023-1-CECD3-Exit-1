package com.cecd.exitmed.presentation.pillDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.domain.repository.DURRepository
import com.cecd.exitmed.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PillDetailDURViewModel @Inject constructor(
    private val durRepository: DURRepository
) : ViewModel() {
    private val _durAgeProhibition = MutableStateFlow<UiState<String?>>(UiState.Loading)
    val durAgeProhibition get() = _durAgeProhibition.asStateFlow()
    private val _durPregnantProhibition = MutableStateFlow<UiState<String?>>(UiState.Loading)
    val durPregnantProhibition get() = _durPregnantProhibition.asStateFlow()
    private val _durCapacityCaution = MutableStateFlow<UiState<String?>>(UiState.Loading)
    val durCapacityCaution get() = _durCapacityCaution.asStateFlow()
    private val _durAdministrationDurationCaution =
        MutableStateFlow<UiState<String?>>(UiState.Loading)
    val durAdministrationDurationCaution get() = _durAdministrationDurationCaution.asStateFlow()
    private val _durSeniorCaution = MutableStateFlow<UiState<String?>>(UiState.Loading)
    val durSeniorCaution get() = _durSeniorCaution.asStateFlow()

    init {
        fetchDURAgeProhibitionContents(ITEM_SEQ)
        fetchDURPregnantProhibitionContents(ITEM_SEQ)
        fetchDURCapacityCautionContents(ITEM_SEQ)
        fetchDURAdministrationDurationCaution(ITEM_SEQ)
        fetchDURSeniorCautionContents(ITEM_SEQ)
    }

    private fun fetchDURAgeProhibitionContents(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURAgeProhibition(itemSeq)
                .onSuccess { item ->
                    if (item[0].PROHBT_CONTENT == null)
                        _durAgeProhibition.value = UiState.Init
                    else
                        _durAgeProhibition.value = UiState.Success(item[0].PROHBT_CONTENT)
                }
                .onFailure { throwable ->
                    _durAgeProhibition.value = UiState.Error(throwable.message)
                    Timber.d(throwable.message)
                }
        }
    }

    private fun fetchDURPregnantProhibitionContents(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURPregnantProhibition(itemSeq)
                .onSuccess { item ->
                    if (item[0].PROHBT_CONTENT == null)
                        _durPregnantProhibition.value = UiState.Init
                    else
                        _durPregnantProhibition.value = UiState.Success(item[0].PROHBT_CONTENT)
                }
                .onFailure { throwable ->
                    _durPregnantProhibition.value = UiState.Error(throwable.message)
                    Timber.d(throwable.message)
                }
        }
    }

    private fun fetchDURCapacityCautionContents(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURCapacityCaution(itemSeq)
                .onSuccess { item ->
                    if (item[0].PROHBT_CONTENT == null)
                        _durCapacityCaution.value = UiState.Init
                    else
                        _durCapacityCaution.value = UiState.Success(item[0].PROHBT_CONTENT)
                }
                .onFailure { throwable ->
                    _durCapacityCaution.value = UiState.Error(throwable.message)
                    Timber.d(throwable.message)
                }
        }
    }

    private fun fetchDURAdministrationDurationCaution(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURAdministrationDurationCaution(itemSeq)
                .onSuccess { item ->
                    if (item[0].PROHBT_CONTENT == null)
                        _durAdministrationDurationCaution.value = UiState.Init
                    else
                        _durAdministrationDurationCaution.value =
                            UiState.Success(item[0].PROHBT_CONTENT)
                }
                .onFailure { throwable ->
                    _durAdministrationDurationCaution.value = UiState.Error(throwable.message)
                    Timber.d(throwable.message)
                }
        }
    }

    private fun fetchDURSeniorCautionContents(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURSeniorCaution(itemSeq)
                .onSuccess { item ->
                    if (item[0].PROHBT_CONTENT == null)
                        _durSeniorCaution.value = UiState.Init
                    else
                        _durSeniorCaution.value = UiState.Success(item[0].PROHBT_CONTENT)
                }
                .onFailure { throwable ->
                    _durSeniorCaution.value = UiState.Error(throwable.message)
                    Timber.d(throwable.message)
                }
        }
    }

    companion object {
        const val ITEM_SEQ = "197200484"
    }
}
