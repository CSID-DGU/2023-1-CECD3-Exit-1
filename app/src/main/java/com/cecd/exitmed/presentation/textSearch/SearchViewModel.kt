package com.cecd.exitmed.presentation.textSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.data.model.request.RequestBookmark
import com.cecd.exitmed.domain.repository.BookmarkRepository
import com.cecd.exitmed.domain.repository.TextSearchRepository
import com.cecd.exitmed.domain.type.Pill
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
    private var _searchListState = MutableStateFlow<UiState<List<Pill>>>(UiState.Loading)
    val searchListState get() = _searchListState.asStateFlow()
    private var _recentSearchTermsState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val recentSearchTermsState get() = _recentSearchTermsState.asStateFlow()
    private var _searchBookmarkedListState =
        MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val searchBookmarkedListState get() = _searchBookmarkedListState.asStateFlow()

    init {
        fetchRecentSearchTerms()
        fetchTextSearchBookmarkedList()
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

    private fun fetchTextSearchBookmarkedList() {
        viewModelScope.launch {
            textSearchRepository.fetchTextSearchBookmarkedList()
                .onSuccess { searchBookMarkedList ->
                    _searchBookmarkedListState.value = UiState.Success(searchBookMarkedList)
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }
}
