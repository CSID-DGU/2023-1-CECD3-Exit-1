package com.cecd.exitmed.presentation.pillCreation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillCreationMemoBinding
import com.cecd.exitmed.util.binding.BindingFragment
import com.cecd.exitmed.util.extension.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillCreationMemoFragment :
    BindingFragment<FragmentPillCreationMemoBinding>(R.layout.fragment_pill_creation_memo) {
    private val viewModel: PillCreationViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
    }

    private fun addListeners() {
        binding.root.setOnClickListener {
            requireActivity().showKeyboard(it, false)
        }
    }
}
