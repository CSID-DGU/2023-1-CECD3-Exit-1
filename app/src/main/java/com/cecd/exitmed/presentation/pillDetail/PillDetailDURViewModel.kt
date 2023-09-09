package com.cecd.exitmed.presentation.pillDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.domain.repository.DURRepository
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
    private val _durAgeProhibition = MutableStateFlow<String?>(null)
    val durAgeProhibition get() = _durAgeProhibition.asStateFlow()
    private val _durPregnantProhibition = MutableStateFlow<String?>(null)
    val durPregnantProhibition get() = _durPregnantProhibition.asStateFlow()
    private val _durCapacityCaution = MutableStateFlow<String?>(null)
    val durCapacityCaution get() = _durCapacityCaution.asStateFlow()
    private val _durSeniorCaution = MutableStateFlow<String?>(null)
    val durSeniorCaution get() = _durSeniorCaution.asStateFlow()

    init {
        fetchDURAgeProhibitionContents("201309959")
        fetchDURPregnantProhibitionContents("201309959")
        fetchDURCapacityCautionContents("201309959")
        fetchDURSeniorCautionContents("201309959")
    }

    private fun fetchDURAgeProhibitionContents(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURAgeProhibition(itemSeq)
                .onSuccess { item ->
                    _durAgeProhibition.value =
                        if (item[0].REMARK != null)
                            item[0].PROHBT_CONTENT + item[0].REMARK
                        else
                            item[0].PROHBT_CONTENT
                }
                .onFailure { throwable ->
                    Timber.d(throwable.message)
                }
        }
    }

    private fun fetchDURPregnantProhibitionContents(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURPregnantProhibition(itemSeq)
                .onSuccess { item ->
                    _durPregnantProhibition.value =
                        if (item[0].REMARK != null)
                            item[0].PROHBT_CONTENT + item[0].REMARK
                        else
                            item[0].PROHBT_CONTENT
                }
                .onFailure { throwable ->
                    Timber.d(throwable.message)
                }
        }
    }

    private fun fetchDURCapacityCautionContents(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURAgeProhibition(itemSeq)
                .onSuccess { item ->
                    _durCapacityCaution.value =
                        if (item[0].REMARK != null)
                            item[0].PROHBT_CONTENT + item[0].REMARK
                        else
                            item[0].PROHBT_CONTENT
                }
                .onFailure { throwable ->
                    Timber.d(throwable.message)
                }
        }
    }

    private fun fetchDURSeniorCautionContents(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURSeniorCaution(itemSeq)
                .onSuccess { item ->
                    _durSeniorCaution.value =
                        if (item[0].REMARK != null)
                            item[0].PROHBT_CONTENT + item[0].REMARK
                        else
                            item[0].PROHBT_CONTENT
                }
                .onFailure { throwable ->
                    Timber.d(throwable.message)
                }
        }
    }
}