package com.cecd.exitmed.presentation.dose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentDoseTimetableBinding
import com.cecd.exitmed.domain.type.DoseTimeTable
import com.cecd.exitmed.util.UiState
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DoseTimeTableFragment :
    BindingFragment<FragmentDoseTimetableBinding>(R.layout.fragment_dose_timetable) {
    private val viewModel: DoseViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        collectData()
    }

    private fun initLayout() {
        val nowDate: String? = LocalDate.now().format(DateTimeFormatter.ofPattern("MM월 dd일"))
        binding.tvDoseTimetableDate.text = nowDate.toString()
    }

    private fun collectData() {
        viewModel.doseTimeTableState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    setDoseTimeTableAdapter(it)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun setDoseTimeTableAdapter(it: UiState.Success<List<DoseTimeTable>>) {
        val doseTimeTableAdapter = DoseTimeTableAdapter()
        binding.rvDoseTimeTable.adapter = doseTimeTableAdapter
        doseTimeTableAdapter.submitList(it.data)
    }
}
