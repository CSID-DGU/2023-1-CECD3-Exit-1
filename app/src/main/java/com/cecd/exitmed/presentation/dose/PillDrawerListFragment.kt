package com.cecd.exitmed.presentation.dose

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDrawerBinding
import com.cecd.exitmed.presentation.pillDrawerDetail.PillDrawerDetailActivity
import com.cecd.exitmed.presentation.search.SearchActivity
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDrawerListFragment :
    BindingFragment<FragmentPillDrawerBinding>(R.layout.fragment_pill_drawer) {
    private val viewModel: DoseViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val pillDrawerAdapter = PillDrawerListAdapter(::moveTOPillDrawerDetail)
        binding.rvPillDrawer.apply {
            adapter = pillDrawerAdapter
        }
        pillDrawerAdapter.submitList(viewModel.mockPillDrawerList)
    }

    private fun addListeners() {
        binding.layoutMoveToSearch.setOnClickListener {
            moveToTextSearch()
        }
    }

    private fun moveTOPillDrawerDetail() {
        startActivity(Intent(requireActivity(), PillDrawerDetailActivity::class.java))
    }

    private fun moveToTextSearch() {
        startActivity(Intent(requireActivity(), SearchActivity::class.java))
    }
}
