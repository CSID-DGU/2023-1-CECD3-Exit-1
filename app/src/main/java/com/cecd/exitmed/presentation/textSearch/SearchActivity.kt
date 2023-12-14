package com.cecd.exitmed.presentation.textSearch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivitySearchBinding
import com.cecd.exitmed.domain.type.Pill
import com.cecd.exitmed.presentation.common.PillListAdapter
import com.cecd.exitmed.presentation.pillDetail.PillDetailActivity
import com.cecd.exitmed.util.UiState
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.binding.setVisibility
import com.cecd.exitmed.util.extension.showKeyboard
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private val searchViewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this

        addListeners()
        collectData()
        setSearchPagerAdapter()
    }

    private fun addListeners() {
        binding.ivSearchDelete.setOnClickListener {
            binding.etSearchBox.text = null
        }
        binding.etSearchBox.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                searchViewModel.textPillSearch()
                showKeyboard(binding.root, false)
                true
            } else {
                false
            }
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    // TODO 서버 통신 후 로직 다시 고민!!
    private fun collectData() {
        searchViewModel.searchCount.flowWithLifecycle(lifecycle).onEach { searchCount ->
            with(binding) {
                tabSearch.setVisibility(searchCount == null)
                vpSearch.setVisibility(searchCount == null)
                linearSearchResult.setVisibility(searchCount != null)
                tvSearchEmpty.setVisibility(searchCount == 0)
                rvSearchList.setVisibility(searchCount != null && searchCount > 0)
            }
        }.launchIn(lifecycleScope)
        searchViewModel.searchListState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    setTextPillSearchAdapter(it.data)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
        searchViewModel.searchTerm.flowWithLifecycle(lifecycle).onEach { searchTerm ->
            Log.d("aaa", searchTerm)
            binding.etSearchBox.setText(searchTerm)
        }.launchIn(lifecycleScope)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        showKeyboard(binding.root, false)
        return super.dispatchTouchEvent(ev)
    }

    private fun setTextPillSearchAdapter(searchList: List<Pill>) {
        val searchListAdapter = PillListAdapter(::moveToPillDetail, ::bookmark)
        binding.rvSearchList.adapter = searchListAdapter
        searchListAdapter.submitList(searchList)
    }

    private fun setSearchPagerAdapter() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(SearchHistoryFragment())
        fragmentList.add(SearchBookmarkFragment())

        val adapter = SearchPagerAdapter(fragmentList, this)
        binding.vpSearch.adapter = adapter

        TabLayoutMediator(binding.tabSearch, binding.vpSearch) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.search_history)
                1 -> tab.text = getString(R.string.search_bookmark)
            }
        }.attach()
    }

    private fun moveToPillDetail(itemSeq: Int) {
        val intent = Intent(this, PillDetailActivity::class.java)
        intent.putExtra(ITEM_SEQ, itemSeq)
        startActivity(intent)
    }

    private fun bookmark(pillItemSeq: Int) {
        searchViewModel.bookmark(pillItemSeq)
    }

    companion object {
        const val ITEM_SEQ = "itemSeq"
    }
}
