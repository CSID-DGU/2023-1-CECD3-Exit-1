package com.cecd.exitmed.presentation.pillDetail

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDetailDrugInteractionBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDetailDrugInteractionFragment :
    BindingFragment<FragmentPillDetailDrugInteractionBinding>(R.layout.fragment_pill_detail_drug_interaction) {
    private val pillDetailViewModel: PillDetailViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectData()
    }

    private fun collectData() {
        pillDetailViewModel.duplicatedPills.flowWithLifecycle(lifecycle)
            .onEach { duplicatedPillList ->
                val duplicatedPills = duplicatedPillList.joinToString(separator = ", ")
                val content = getString(
                    R.string.pill_detail_drug_interaction_content,
                    Html.fromHtml(duplicatedPills, Html.FROM_HTML_MODE_LEGACY)
                )
                binding.tvPillDetailDrugInteractionContent.text =
                    Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
            }.launchIn(lifecycleScope)
    }
}
