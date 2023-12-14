package com.cecd.exitmed.presentation.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.domain.repository.MyRepository
import com.cecd.exitmed.domain.type.Pill
import com.cecd.exitmed.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {
    private var _myBookmarkedListState = MutableStateFlow<UiState<List<Pill>>>(UiState.Loading)
    val myBookmarkedListState get() = _myBookmarkedListState.asStateFlow()

    fun fetchMyBookmarkedList() {
        viewModelScope.launch {
            myRepository.fetchMyBookmarkedList()
                .onSuccess { myBookmarkedListState ->
                    _myBookmarkedListState.value = UiState.Success(myBookmarkedListState)
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }
}
