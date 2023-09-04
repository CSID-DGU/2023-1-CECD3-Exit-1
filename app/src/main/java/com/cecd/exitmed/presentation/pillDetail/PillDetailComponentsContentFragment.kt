package com.cecd.exitmed.presentation.pillDetail

import android.os.Bundle
import android.view.View
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDetailComponentsContentBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDetailComponentsContentFragment :
    BindingFragment<FragmentPillDetailComponentsContentBinding>(R.layout.fragment_pill_detail_components_content) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
