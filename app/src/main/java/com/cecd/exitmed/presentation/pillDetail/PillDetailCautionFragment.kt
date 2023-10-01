package com.cecd.exitmed.presentation.pillDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDetailCautionBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDetailCautionFragment :
    BindingFragment<FragmentPillDetailCautionBinding>(R.layout.fragment_pill_detail_caution) {
    private val pillDetailViewModel: PillDetailViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectData()
    }

    private fun collectData() {
        pillDetailViewModel.pillCaution.flowWithLifecycle(lifecycle).onEach { pillCaution ->
            binding.tvPillDetailCautionContent.text = pillCaution
        }.launchIn(lifecycleScope)
    }
}
