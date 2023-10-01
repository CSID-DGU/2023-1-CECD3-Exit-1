package com.cecd.exitmed.presentation.pillDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDetailMedicationBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDetailMedicationFragment :
    BindingFragment<FragmentPillDetailMedicationBinding>(R.layout.fragment_pill_detail_medication) {
    private val pillDetailViewModel: PillDetailViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectData()
    }

    private fun collectData() {
        pillDetailViewModel.pillEffect.flowWithLifecycle(lifecycle).onEach { pillEffect->
            binding.tvPillDetailMedicationEffectContent.text = pillEffect
        }.launchIn(lifecycleScope)
        pillDetailViewModel.pillStorageMethod.flowWithLifecycle(lifecycle).onEach { pillStorageMethod->
            binding.tvPillDetailMedicationStorageMethodContent.text = pillStorageMethod
        }.launchIn(lifecycleScope)
    }
}
