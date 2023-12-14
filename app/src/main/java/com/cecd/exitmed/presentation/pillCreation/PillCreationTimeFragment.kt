package com.cecd.exitmed.presentation.pillCreation

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillCreationTimeBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class PillCreationTimeFragment :
    BindingFragment<FragmentPillCreationTimeBinding>(R.layout.fragment_pill_creation_time) {
    private lateinit var pillCreationTimeAdapter: PillCreationTimeAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        addListeners()
        collectData()
    }

    private fun initLayout() {
        binding.tvPillCreationTime.text = getString(R.string.Pill_creation_count, 0)
        pillCreationTimeAdapter = PillCreationTimeAdapter(::showTimePickerDialog)
        binding.rvPillCreationTimeList.adapter = pillCreationTimeAdapter
    }

    private fun addListeners() {
        binding.ivPillCreationTimeAdd.setOnClickListener {
            pillCreationTimeAdapter.addItem(pillCreationTimeAdapter.itemCount)
        }
        binding.ivPillCreationTimeRemove.setOnClickListener {
            pillCreationTimeAdapter.removeItem(pillCreationTimeAdapter.itemCount - 1)
        }
    }

    private fun collectData() {
        pillCreationTimeAdapter.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                updateCount()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                updateCount()
            }
        })
    }

    private fun updateCount() {
        binding.tvPillCreationTime.text =
            String.format("섭취횟수 %d회", pillCreationTimeAdapter.itemCount)
    }

    private fun showTimePickerDialog(position: Int) {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val time = String.format("%02d:%02d", hourOfDay, minute)
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
}
