package com.cecd.exitmed.presentation.my

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityBookmarkListBinding
import com.cecd.exitmed.presentation.common.PillListAdapter
import com.cecd.exitmed.presentation.pillDetail.PillDetailActivity
import com.cecd.exitmed.presentation.textSearch.SearchViewModel
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPillListActivity :
    BindingActivity<ActivityBookmarkListBinding>(R.layout.activity_bookmark_list) {
    // TODO 뷰모델 수정
    private val viewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val pillAdapter = PillListAdapter(::moveToPillDetail)
        binding.rvBookmarkPillList.adapter = pillAdapter
        pillAdapter.submitList(viewModel.mockSearchList)
    }

    private fun addListeners() {
        binding.layoutToolbar.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun moveToPillDetail() {
        startActivity(Intent(this, PillDetailActivity::class.java))
    }
}
