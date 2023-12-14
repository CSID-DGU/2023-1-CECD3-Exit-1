package com.cecd.exitmed.presentation.imageSearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentImageSearchTop5AccuracyBinding
import com.cecd.exitmed.presentation.pillDetail.PillDetailActivity
import com.cecd.exitmed.util.binding.BindingFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageSearchResultTop5Fragment :
    BindingFragment<FragmentImageSearchTop5AccuracyBinding>(R.layout.fragment_image_search_top5_accuracy) {
    private val viewModel: ImageSearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val adapter = ImageSearchTop5Adapter()
        binding.vpTop5Accuracy.adapter = adapter
        adapter.submitList(
            viewModel.imageSearchInfoLists.value.subList(
                1,
                viewModel.imageSearchInfoLists.value.size
            )
        )
        TabLayoutMediator(
            binding.top5AccuracyTabIndicator,
            binding.vpTop5Accuracy
        ) { _, _ -> }.attach()
    }

    private fun addListeners() {
        binding.btnYes.setOnClickListener {
            val currentViewPagerPos = binding.vpTop5Accuracy.currentItem
            val intent = Intent(requireActivity(), PillDetailActivity::class.java)
            intent.putExtra(
                ImageSearchResultTop1Fragment.ITEM_SEQ,
                viewModel.imageSearchInfoLists.value[currentViewPagerPos + 1].pillItemSequence
            )
            startActivity(intent)
        }
    }
}
