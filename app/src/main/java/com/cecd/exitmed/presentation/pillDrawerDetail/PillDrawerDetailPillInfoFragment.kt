package com.cecd.exitmed.presentation.pillDrawerDetail

import android.os.Bundle
import android.view.View
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDrawerDetailPillInfoBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDrawerDetailPillInfoFragment :
    BindingFragment<FragmentPillDrawerDetailPillInfoBinding>(R.layout.fragment_pill_drawer_detail_pill_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
