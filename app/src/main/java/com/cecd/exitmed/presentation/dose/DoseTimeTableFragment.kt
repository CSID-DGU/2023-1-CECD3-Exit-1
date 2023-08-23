package com.cecd.exitmed.presentation.dose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentDoseTimetableBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class DoseTimeTableFragment :
    BindingFragment<FragmentDoseTimetableBinding>(R.layout.fragment_dose_timetable) {
    private val viewModel: DoseViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
    }

    private fun initLayout() {
        val doseTimeTableAdapter = DoseTimeTableAdapter()
        binding.rvDoseTimeTable.adapter = doseTimeTableAdapter
        doseTimeTableAdapter.submitList(viewModel.mockDoseTimeTable)

        val nowDate: String? = LocalDate.now().format(DateTimeFormatter.ofPattern("MM월 dd일"))
        binding.tvDoseTimetableDate.text = nowDate.toString()
    }
}
