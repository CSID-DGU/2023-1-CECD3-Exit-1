package com.cecd.exitmed.presentation.textSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.domain.repository.TextSearchRepository
import com.cecd.exitmed.domain.type.SearchPill
import com.cecd.exitmed.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val textSearchRepository: TextSearchRepository
) : ViewModel() {
    val searchText = MutableStateFlow("")
    private var _searchCount = MutableStateFlow<Int?>(5)
    val searchCount get() = _searchCount.asStateFlow()
    private var _searchList = MutableStateFlow<UiState<List<SearchPill>>>(UiState.Empty)
    val searchList get() = _searchList.asStateFlow()

    fun textPillSearch() {
        viewModelScope.launch {
            textSearchRepository.textPillSearch(searchText.value)
                .onSuccess { searchList ->
                    _searchList.value = UiState.Success(searchList)
                    _searchCount.value = UiState.Success(searchList).data.size
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    val mockHistoryList = listOf(
        "타이레놀",
        "스리반정",
        "자나팜정",
        "인데놀정",
    )

    val mockBookmarkList = listOf(
        "스리반정",
        "에나팜정",
        "자나팜정",
        "인데놀정",
        "환인트라조돈캡슐",
        "타이레놀"
    )
}
