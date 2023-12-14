package com.cecd.exitmed.presentation.my

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityBookmarkListBinding
import com.cecd.exitmed.domain.type.Pill
import com.cecd.exitmed.presentation.common.PillListAdapter
import com.cecd.exitmed.presentation.pillDetail.PillDetailActivity
import com.cecd.exitmed.presentation.textSearch.SearchActivity
import com.cecd.exitmed.util.UiState
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyPillListActivity :
    BindingActivity<ActivityBookmarkListBinding>(R.layout.activity_bookmark_list) {
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchMyBookmarkedList()
        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.layoutToolbar.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun collectData() {
        viewModel.myBookmarkedListState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    setBookmarkListAdapter(it)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun setBookmarkListAdapter(it: UiState.Success<List<Pill>>) {
        val bookmarkedListAdapter = PillListAdapter(::moveToPillDetail, ::bookmark)
        binding.rvBookmarkPillList.adapter = bookmarkedListAdapter
        bookmarkedListAdapter.submitList(it.data)
    }

    private fun moveToPillDetail(itemSeq: Int) {
        val intent = Intent(this, PillDetailActivity::class.java)
        intent.putExtra(SearchActivity.ITEM_SEQ, itemSeq)
        startActivity(intent)
    }

    // TODO 임의 로직, 삭제
    private fun bookmark(itemSeq: Int) {
    }
}
