package com.cecd.exitmed.presentation.textSearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentSearchHistoryBinding
import com.cecd.exitmed.presentation.pillDetail.PillDetailActivity
import com.cecd.exitmed.util.UiState
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchHistoryFragment :
    BindingFragment<FragmentSearchHistoryBinding>(R.layout.fragment_search_history) {
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectData()
    }

    private fun collectData() {
        searchViewModel.recentSearchTermsState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    setRecentSearchTermAdapter(it)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun setRecentSearchTermAdapter(it: UiState.Success<List<String>>) {
        val historyAdapter = SearchHistoryAdapter(::moveToPillDetail)
        binding.rvSearchHistory.adapter = historyAdapter
        historyAdapter.setHistoryList(it.data.toMutableList())
    }

    private fun moveToPillDetail() {
        startActivity(Intent(requireActivity(), PillDetailActivity::class.java))
    }
}
