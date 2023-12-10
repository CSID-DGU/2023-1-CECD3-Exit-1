package com.cecd.exitmed.presentation.pillCreation

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
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
        // TODO adapter 내 item 변화 관찰
        pillCreationTimeAdapter.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                viewModel.setPillTimeList(pillCreationTimeAdapter.timeList)
            }
        })
        viewModel.pillTimeList.flowWithLifecycle(lifecycle).onEach { pillTimeList ->
            binding.tvPillCreationTime.text = String.format("섭취횟수 %d회", pillTimeList.size)
        }.launchIn(lifecycleScope)
    }

    private fun showTimePickerDialog(position: Int) {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val time =
                if (minute == 0) {
                    String.format("%d:00", hourOfDay)
                } else {
                    String.format("%d:%d", hourOfDay, minute)
                }
            pillCreationTimeAdapter.setItem(time, position)
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
            "오전 $hourOfDay:$minute"
        else if (hourOfDay == 12)
            "오후 $hourOfDay:$minute"
        else
            "오후 ${hourOfDay - 12}:$minute"
    }
}
