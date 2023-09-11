package com.cecd.exitmed.presentation.pillDetail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDetailDurBinding
import com.cecd.exitmed.util.UiState
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDetailDURFragment :
    BindingFragment<FragmentPillDetailDurBinding>(R.layout.fragment_pill_detail_dur) {
    private val durViewModel: PillDetailDURViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = durViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        addListeners()
        collectData()
    }

    private fun collectData() {
        durViewModel.durAgeProhibition.flowWithLifecycle(lifecycle).onEach {
            setDURContents(
                uiState = it,
                contentLayout = binding.layoutPillDetailDurAgeProhibit,
                contentTextView = binding.tvPillDetailDurAgeProhibitContent,
                resInitContentString = R.string.init_dur_age_prohibition_contents
            )
        }.launchIn(lifecycleScope)
        durViewModel.durPregnantProhibition.flowWithLifecycle(lifecycle).onEach {
            setDURContents(
                uiState = it,
                contentLayout = binding.layoutPillDetailDurPregnantProhibit,
                contentTextView = binding.tvPillDetailDurPregnantProhibitContent,
                resInitContentString = R.string.init_dur_pregnant_prohibition_contents
            )
        }.launchIn(lifecycleScope)
        durViewModel.durCapacityCaution.flowWithLifecycle(lifecycle).onEach {
            setDURContents(
                uiState = it,
                contentLayout = binding.layoutPillDetailDurCapacityCaution,
                contentTextView = binding.tvPillDetailDurCapacityCautionContent,
                resInitContentString = R.string.init_dur_capacity_caution_contents
            )
        }.launchIn(lifecycleScope)
        durViewModel.durAdministrationDurationCaution.flowWithLifecycle(lifecycle).onEach {
            setDURContents(
                uiState = it,
                contentLayout = binding.layoutPillDetailDurAdministrationDurationCaution,
                contentTextView = binding.tvPillDetailDurAdministrationDurationCautionContent,
                resInitContentString = R.string.init_dur_administration_duration_caution_contents
            )
        }.launchIn(lifecycleScope)
        durViewModel.durSeniorCaution.flowWithLifecycle(lifecycle).onEach {
            setDURContents(
                uiState = it,
                contentLayout = binding.layoutPillDetailDurElderCaution,
                contentTextView = binding.tvPillDetailDurElderCautionContent,
                resInitContentString = R.string.init_dur_senior_caution_contents
            )
        }.launchIn(lifecycleScope)
    }

    private fun addListeners() {
        binding.ivDurQuestionMark.setOnClickListener {
            binding.layoutDurMeaningBubble.visibility = View.VISIBLE
        }
        binding.ivDurMeaningClose.setOnClickListener {
            binding.layoutDurMeaningBubble.visibility = View.INVISIBLE
        }
    }

    private fun setDURContents(
        uiState: UiState<String?>,
        contentLayout: View,
        contentTextView: TextView,
        resInitContentString: Int
    ) {
        when (uiState) {
            is UiState.Loading -> {
                contentTextView.text =
                    getString(R.string.loading_dur_contents)
            }

            is UiState.Init -> {
                contentTextView.text =
                    getString(resInitContentString)
            }

            is UiState.Success -> {
                contentTextView.text = uiState.data
            }

            is UiState.Error -> {
                contentLayout.visibility = View.GONE
            }

            else -> {}
        }
    }
}
