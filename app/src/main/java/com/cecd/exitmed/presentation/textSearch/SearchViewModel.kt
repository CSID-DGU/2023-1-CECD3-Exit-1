package com.cecd.exitmed.presentation.textSearch

import androidx.lifecycle.ViewModel
import com.cecd.exitmed.domain.type.SearchPill
import com.cecd.exitmed.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {
    val searchText = MutableStateFlow("")
    private var _searchCount = MutableStateFlow<Int?>(5)
    val searchCount get() = _searchCount.asStateFlow()
    private var _searchList = MutableStateFlow<UiState<List<SearchPill>>>(UiState.Empty)
    val searchList get() = _searchList.asStateFlow()

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

    val mockSearchList = listOf(
        SearchPill(
            1,
            "타이레놀",
            "진통제"
        ),
        SearchPill(
            1,
            "타이레놀",
            "진통제"
        ),
        SearchPill(
            1,
            "타이레놀",
            "진통제"
        ),
        SearchPill(
            1,
            "타이레놀",
            "진통제"
        ),
        SearchPill(
            1,
            "타이레놀",
            "진통제"
        ),
    )
}
