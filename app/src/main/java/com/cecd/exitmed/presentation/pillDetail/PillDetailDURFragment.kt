package com.cecd.exitmed.presentation.pillDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDetailDurBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDetailDURFragment :
    BindingFragment<FragmentPillDetailDurBinding>(R.layout.fragment_pill_detail_dur) {
    private val durViewModel: PillDetailDURViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = durViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        addListeners()
    }

    private fun addListeners() {
        binding.ivDurQuestionMark.setOnClickListener {
            binding.layoutDurMeaningBubble.visibility = View.VISIBLE
        }
        binding.ivDurMeaningClose.setOnClickListener {
            binding.layoutDurMeaningBubble.visibility = View.INVISIBLE
        }
    }
}
