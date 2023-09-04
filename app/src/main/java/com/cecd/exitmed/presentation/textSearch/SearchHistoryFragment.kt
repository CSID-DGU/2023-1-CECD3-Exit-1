package com.cecd.exitmed.presentation.textSearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentSearchHistoryBinding
import com.cecd.exitmed.presentation.pillDetail.PillDetailActivity
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHistoryFragment :
    BindingFragment<FragmentSearchHistoryBinding>(R.layout.fragment_search_history) {
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
    }

    private fun initLayout() {
        val historyAdapter = SearchHistoryAdapter(::moveToPillDetail)
        binding.rvSearchHistory.adapter = historyAdapter
        historyAdapter.setHistoryList(searchViewModel.mockHistoryList.toMutableList())
    }

    private fun moveToPillDetail() {
        startActivity(Intent(requireActivity(), PillDetailActivity::class.java))
    }
}
