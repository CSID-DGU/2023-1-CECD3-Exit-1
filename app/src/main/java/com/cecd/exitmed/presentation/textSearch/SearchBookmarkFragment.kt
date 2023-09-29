package com.cecd.exitmed.presentation.textSearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentSearchBookmarkBinding
import com.cecd.exitmed.presentation.pillDetail.PillDetailActivity
import com.cecd.exitmed.util.UiState
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchBookmarkFragment :
    BindingFragment<FragmentSearchBookmarkBinding>(R.layout.fragment_search_bookmark) {
    private val searchViewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectData()
    }

    private fun collectData() {
        searchViewModel.searchBookmarkedListState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    setSearchBookmarkListAdapter(it)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun setSearchBookmarkListAdapter(it: UiState.Success<List<String>>) {
        val adapter = SearchBookmarkAdapter(::moveToPillDetail)
        binding.rvSearchBookmark.adapter = adapter
        adapter.setBookmarkList(it.data.toMutableList())
    }

    private fun moveToPillDetail() {
        startActivity(Intent(requireActivity(), PillDetailActivity::class.java))
    }
}
