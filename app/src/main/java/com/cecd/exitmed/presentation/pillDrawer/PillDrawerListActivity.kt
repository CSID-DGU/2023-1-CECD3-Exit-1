package com.cecd.exitmed.presentation.pillDrawer

import android.os.Bundle
import androidx.activity.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityPillDrawerBinding
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDrawerListActivity :
    BindingActivity<ActivityPillDrawerBinding>(R.layout.activity_pill_drawer) {
    private val viewModel: PillDrawerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val pillDrawerAdapter = PillDrawerListAdapter()
        binding.rvPillDrawer.apply {
            adapter = pillDrawerAdapter
        }
        pillDrawerAdapter.submitList(viewModel.mockPillDrawerList)
    }

    private fun addListeners() {
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}
