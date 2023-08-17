package com.cecd.exitmed.presentation.pillCreation

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillCreationTimeBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Calendar

@AndroidEntryPoint
class PillCreationTimeFragment :
    BindingFragment<FragmentPillCreationTimeBinding>(R.layout.fragment_pill_creation_time) {
    private val viewModel: PillCreationViewModel by activityViewModels()
    private lateinit var pillCreationTimeAdapter: PillCreationTimeAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        addListeners()
        collectData()
    }

    private fun initLayout() {
        pillCreationTimeAdapter = PillCreationTimeAdapter(::showTimePickerDialog)
        binding.rvPillCreationTimeList.adapter = pillCreationTimeAdapter
    }

    private fun addListeners() {
        binding.ivPillCreationTimeAdd.setOnClickListener {
            pillCreationTimeAdapter.addItem()
        }
        binding.ivPillCreationTimeRemove.setOnClickListener {
            pillCreationTimeAdapter.removeItem(pillCreationTimeAdapter.itemCount - 1)
        }
    }

    private fun collectData() {
        viewModel.pagePosition.flowWithLifecycle(lifecycle).onEach {
            if (it == 3)
                viewModel.setPillTimeList(pillCreationTimeAdapter.timeList)
        }.launchIn(lifecycleScope)
    }

    private fun showTimePickerDialog(position: Int) {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            pillCreationTimeAdapter.setItem(set12HourClock(hourOfDay, minute), position)
        }
        TimePickerDialog(
            requireActivity(),
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()
    }

    // TODO 로직 다시 보기
    private fun set12HourClock(hourOfDay: Int, minute: Int): String {
        return if (hourOfDay < 12)
            "오전 ${hourOfDay}:${minute}"
        else if (hourOfDay == 12)
            "오후 ${hourOfDay}:${minute}"
        else
            "오후 ${hourOfDay - 12}:${minute}"
    }
}