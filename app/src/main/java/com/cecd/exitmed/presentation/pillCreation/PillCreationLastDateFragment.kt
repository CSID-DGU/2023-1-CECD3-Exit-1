package com.cecd.exitmed.presentation.pillCreation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillCreationLastDateBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Calendar

@AndroidEntryPoint
class PillCreationLastDateFragment :
    BindingFragment<FragmentPillCreationLastDateBinding>(R.layout.fragment_pill_creation_last_date) {
    private val viewModel: PillCreationViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        showCalenderDialog()
    }

    private fun showCalenderDialog() {
        binding.ivPillCreationCalendar.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val dateString = String.format(
                        getString(R.string.pill_creation_date_format),
                        year,
                        month + 1,
                        dayOfMonth
                    )
                    binding.tvPillCreationDate.text = dateString
                    viewModel.setLastDoseDate(dateString)
                    Timber.d(dateString)
                }
            DatePickerDialog(
                requireActivity(),
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
            ).show()
        }
    }
}
