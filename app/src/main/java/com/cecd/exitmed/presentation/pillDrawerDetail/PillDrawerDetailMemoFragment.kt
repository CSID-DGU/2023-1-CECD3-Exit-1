package com.cecd.exitmed.presentation.pillDrawerDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDrawerDetailMemoBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDrawerDetailMemoFragment :
    BindingFragment<FragmentPillDrawerDetailMemoBinding>(R.layout.fragment_pill_drawer_detail_memo) {
    private val viewModel: PillDrawerDetailViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
    }

    private fun initLayout() {
        binding.tvPillDrawerMemo.text =
            viewModel.drawerPillDetail.value?.comment
    }
}
