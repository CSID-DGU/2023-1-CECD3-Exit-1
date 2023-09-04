package com.cecd.exitmed.presentation.pillDetail

import android.os.Bundle
import android.view.View
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDetailMedicationBinding
import com.cecd.exitmed.databinding.FragmentPillDetailUsageBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDetailUsageFragment :
    BindingFragment<FragmentPillDetailUsageBinding>(R.layout.fragment_pill_detail_usage) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
