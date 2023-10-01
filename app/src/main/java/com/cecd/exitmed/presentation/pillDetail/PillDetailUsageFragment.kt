package com.cecd.exitmed.presentation.pillDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDetailUsageBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDetailUsageFragment :
    BindingFragment<FragmentPillDetailUsageBinding>(R.layout.fragment_pill_detail_usage) {
    private val pillDetailViewModel: PillDetailViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectData()
    }

    private fun collectData() {
        pillDetailViewModel.pillDosageMethod.flowWithLifecycle(lifecycle)
            .onEach { pillDosageMethod ->
                binding.tvPillDetailUsageContent.text = pillDosageMethod
            }.launchIn(lifecycleScope)
    }
}
