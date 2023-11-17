package com.cecd.exitmed.presentation.pillCreation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillCreationPeriodBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillCreationPeriodFragment :
    BindingFragment<FragmentPillCreationPeriodBinding>(R.layout.fragment_pill_creation_period) {
    private val viewModel: PillCreationViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
