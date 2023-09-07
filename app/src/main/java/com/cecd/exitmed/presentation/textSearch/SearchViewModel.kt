package com.cecd.exitmed.presentation.textSearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.domain.repository.DURRepository
import com.cecd.exitmed.domain.type.SearchPill
import com.cecd.exitmed.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val durRepository: DURRepository
) : ViewModel() {
    val searchText = MutableStateFlow("")
    private var _searchCount = MutableStateFlow<Int?>(5)
    val searchCount get() = _searchCount.asStateFlow()
    private var _searchList = MutableStateFlow<UiState<List<SearchPill>>>(UiState.Empty)
    val searchList get() = _searchList.asStateFlow()

    init {
        fetchDUR("201309959")
    }

    private fun fetchDUR(itemSeq: String) {
        viewModelScope.launch {
            durRepository.fetchDURSeniorCaution(itemSeq)
                .onSuccess { item ->
                    Log.d("aaaaa", item[0].PROHBT_CONTENT)
                }
                .onFailure { thorwable ->
                    thorwable.message?.let { msg ->
                        Log.d("aaaa", msg)
                    }
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
