package com.cecd.exitmed.presentation.textSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.data.model.request.RequestBookmark
import com.cecd.exitmed.domain.repository.BookmarkRepository
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
    private val textSearchRepository: TextSearchRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    val searchText = MutableStateFlow("")
    private var _searchCount = MutableStateFlow<Int?>(null)
    val searchCount get() = _searchCount.asStateFlow()
    private var _searchListState = MutableStateFlow<UiState<List<SearchPill>>>(UiState.Loading)
    val searchListState get() = _searchListState.asStateFlow()
    private var _recentSearchTermsState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val recentSearchTermsState get() = _recentSearchTermsState.asStateFlow()

    init {
        fetchRecentSearchTerms()
    }

    fun textPillSearch() {
        viewModelScope.launch {
            textSearchRepository.textPillSearch(searchText.value)
                .onSuccess { searchList ->
                    _searchListState.value = UiState.Success(searchList)
                    _searchCount.value = UiState.Success(searchList).data.size
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    private fun fetchRecentSearchTerms() {
        viewModelScope.launch {
            textSearchRepository.fetchRecentSearchTerm()
                .onSuccess { recentSearchTerms ->
                    _recentSearchTermsState.value = UiState.Success(recentSearchTerms)
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    // TODO detail view로 이동
    fun bookmark(pillItemSeq: Int) {
        viewModelScope.launch {
            bookmarkRepository.bookmark(RequestBookmark(pillItemSeq))
                .onSuccess { isBookmarked ->
                    Timber.tag("bookmark").e(isBookmarked.toString())
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    val mockBookmarkList = listOf(
        "스리반정",
        "에나팜정",
        "자나팜정",
        "인데놀정",
        "환인트라조돈캡슐",
        "타이레놀"
    )
}
