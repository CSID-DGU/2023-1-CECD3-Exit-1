package com.cecd.exitmed.presentation.dose

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDrawerBinding
import com.cecd.exitmed.domain.type.PillDrawerData
import com.cecd.exitmed.presentation.pillDrawerDetail.PillDrawerDetailActivity
import com.cecd.exitmed.presentation.textSearch.SearchActivity
import com.cecd.exitmed.util.UiState
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDrawerListFragment :
    BindingFragment<FragmentPillDrawerBinding>(R.layout.fragment_pill_drawer) {
    private val viewModel: DoseViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.layoutMoveToSearch.setOnClickListener {
            moveToTextSearch()
        }
    }

    private fun collectData() {
        viewModel.myDrawerListState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    setMyPillDrawerListAdapter(it)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)

        // TODO 알람 상태를 local에서 임시적으로 수정할 순 업는가?
        viewModel.alarmState.flowWithLifecycle(lifecycle).onEach {
            viewModel.fetchMyDrawerList()
        }.launchIn(lifecycleScope)
    }

    private fun setMyPillDrawerListAdapter(it: UiState.Success<List<PillDrawerData>>) {
        val pillDrawerAdapter = PillDrawerListAdapter(::moveToPillDrawerDetail, ::onOffDoseAlarm)
        binding.rvPillDrawer.adapter = pillDrawerAdapter
        pillDrawerAdapter.submitList(it.data)
    }

    private fun onOffDoseAlarm(itemSeq: Int) {
        viewModel.onOffDoseAlarm(itemSeq)
    }

    private fun moveToPillDrawerDetail() {
        startActivity(Intent(requireActivity(), PillDrawerDetailActivity::class.java))
    }

    private fun moveToTextSearch() {
        startActivity(Intent(requireActivity(), SearchActivity::class.java))
    }
}
