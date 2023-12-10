package com.cecd.exitmed.presentation.imageSearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentImageSearchBinding
import com.cecd.exitmed.util.ContentUriRequestBody
import com.cecd.exitmed.util.UiState
import com.cecd.exitmed.util.binding.BindingFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ImageSearchFragment :
    BindingFragment<FragmentImageSearchBinding>(R.layout.fragment_image_search) {
    private val viewModel: ImageSearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.ivImageSearchPill.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .compress(1024)
                .cropSquare()
                .start()
        }
        binding.toolbar.ivBack.setOnClickListener {
            // 뒤로가기
            requireActivity().finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let { image ->
            binding.ivImageSearchPill.setImageURI(image.data)
            binding.btnImageSearch.setOnClickListener {
                image.data?.let {
                    viewModel.setRequestBody(ContentUriRequestBody(requireContext(), it))
                    viewModel.searchImagePill()
                }
            }
        }
        binding.ivAddAImage.visibility = View.GONE
    }

    private fun collectData() {
        viewModel.imageSearchState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    parentFragmentManager.beginTransaction().apply {
                        replace(
                            R.id.container_image_search_result,
                            ImageSearchResultTop1Fragment()
                        )
                        commit()
                    }
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }
}

