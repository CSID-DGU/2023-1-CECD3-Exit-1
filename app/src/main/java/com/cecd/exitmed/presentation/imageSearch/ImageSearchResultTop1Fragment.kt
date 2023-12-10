package com.cecd.exitmed.presentation.imageSearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentImageSearchTop1AccuracyBinding
import com.cecd.exitmed.presentation.pillDetail.PillDetailActivity
import com.cecd.exitmed.util.binding.BindingFragment
import com.cecd.exitmed.util.binding.setImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageSearchResultTop1Fragment :
    BindingFragment<FragmentImageSearchTop1AccuracyBinding>(R.layout.fragment_image_search_top1_accuracy) {
    private val viewModel: ImageSearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        with(viewModel.imageSearchInfoLists.value[0]) {
            binding.ivTopAccuracyImage.setImage(this.imageLink)
            binding.tvTopAccuracyPillName.text = getString(R.string.image_search_pill_name, this.pillName)
            binding.tvTopAccuracyPillShpae.text = getString(R.string.image_search_pill_shape, this.shape)
        }
    }

    private fun addListeners() {
        binding.btnNo.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(
                    R.id.container_image_search_result,
                    ImageSearchResultTop5Fragment()
                )
                commit()
            }
        }
        binding.btnYes.setOnClickListener {
            val intent = Intent(requireActivity(), PillDetailActivity::class.java)
            intent.putExtra(ITEM_SEQ, viewModel.imageSearchInfoLists.value[0].pillItemSequence)
            startActivity(intent)
        }
    }

    companion object {
        const val ITEM_SEQ = "itemSeq"
    }
}
