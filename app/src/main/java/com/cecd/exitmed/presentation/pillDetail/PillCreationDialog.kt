package com.cecd.exitmed.presentation.pillDetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentCreatePillDrawerDialogBinding
import com.cecd.exitmed.presentation.pillCreation.PillCreationActivity
import com.cecd.exitmed.util.binding.BindingDialogFragment

class PillCreationDialog :
    BindingDialogFragment<FragmentCreatePillDrawerDialogBinding>(R.layout.fragment_create_pill_drawer_dialog) {
    private var duplicatedPillSize: Int? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        duplicatedPillSize = arguments?.getInt(DUPLICATED_PILL_SIZE)
        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val dialogDescription =
            when (duplicatedPillSize) {
                0 -> getString(R.string.pill_creation_dialog_description_basic)
                else -> getString(R.string.pill_creation_dialog_description_duplicated)
            }
        binding.tvPillCreationDialogDescription.text = dialogDescription
    }

    private fun addListeners() {
        binding.no.setOnClickListener {
            dismiss()
        }
        binding.yes.setOnClickListener {
            moveToAddPillDrawer()
            dismiss()
        }
    }

    private fun moveToAddPillDrawer() {
        val intent = Intent(requireContext(), PillCreationActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val DUPLICATED_PILL_SIZE = "duplicatedPillSize"
    }
}