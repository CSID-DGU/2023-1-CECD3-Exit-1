package com.cecd.exitmed.presentation.pillDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.data.model.request.RequestBookmark
import com.cecd.exitmed.domain.repository.BookmarkRepository
import com.cecd.exitmed.domain.repository.PillDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PillDetailViewModel @Inject constructor(
    private val pillDetailRepository: PillDetailRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _pillName = MutableStateFlow("")
    val pillName get() = _pillName.asStateFlow()
    private val _pillClassification = MutableStateFlow("")
    val pillClassification get() = _pillClassification.asStateFlow()
    private val _pillImage = MutableStateFlow("")
    val pillImage get() = _pillImage.asStateFlow()
    private val _isBookMarked = MutableStateFlow(false)
    val isBookMarked get() = _isBookMarked.asStateFlow()
    private val _pillEffect = MutableStateFlow("")
    val pillEffect get() = _pillEffect.asStateFlow()
    private val _pillStorageMethod = MutableStateFlow("")
    val pillStorageMethod get() = _pillStorageMethod.asStateFlow()
    private val _pillIngredient = MutableStateFlow("")
    val pillIngredient get() = _pillIngredient.asStateFlow()
    private val _pillDosageMethod = MutableStateFlow("")
    val pillDosageMethod get() = _pillDosageMethod.asStateFlow()
    private val _pillCaution = MutableStateFlow("")
    val pillCaution get() = _pillCaution.asStateFlow()
    private val _duplicatedPills = MutableStateFlow<List<String>>(listOf())
    val duplicatedPills get() = _duplicatedPills.asStateFlow()

    fun fetchPillDetail(itemSeq: Int) {
        viewModelScope.launch {
            pillDetailRepository.fetchPillDetail(itemSeq)
                .onSuccess { pillDetail ->
                    _pillName.value = pillDetail.pillName
                    _pillClassification.value = pillDetail.classification
                    _pillImage.value = pillDetail.imageLink
                    _isBookMarked.value = pillDetail.favorite
                    _pillEffect.value = pillDetail.effect
                    _pillStorageMethod.value = pillDetail.storage
                    _pillIngredient.value = pillDetail.ingredient
                    _pillDosageMethod.value = pillDetail.dosage
                    _pillCaution.value = pillDetail.warning
                    _duplicatedPills.value = pillDetail.duplicatedPills
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    fun bookmark(pillItemSeq: Int) {
        viewModelScope.launch {
            bookmarkRepository.bookmark(RequestBookmark(pillItemSeq))
                .onSuccess { isBookmarked ->
                    fetchPillDetail(pillItemSeq)
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }
}
