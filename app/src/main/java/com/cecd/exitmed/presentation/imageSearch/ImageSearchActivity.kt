package com.cecd.exitmed.presentation.imageSearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.data.model.response.toParcelableImageSearch
import com.cecd.exitmed.databinding.ActivityImageSearchBinding
import com.cecd.exitmed.presentation.model.ImageSearchModel
import com.cecd.exitmed.util.ContentUriRequestBody
import com.cecd.exitmed.util.binding.BindingActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ImageSearchActivity :
    BindingActivity<ActivityImageSearchBinding>(R.layout.activity_image_search) {
    private val viewModel: ImageSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let { image ->
            binding.ivImageSearchPill.setImageURI(image.data)
            binding.btnImageSearch.setOnClickListener {
                image.data?.let {
                    viewModel.setRequestBody(ContentUriRequestBody(this, it))
                    viewModel.searchImagePill()
                }
            }
        }
        binding.ivAddAImage.visibility = View.GONE
    }

    private fun collectData() {
        viewModel.topAccuracyImageInfo.flowWithLifecycle(lifecycle).onEach { imageInfo ->
            val imageInfo = imageInfo?.toParcelableImageSearch()
            imageInfo?.let { moveToTopAccuracy(it) }
        }.launchIn(lifecycleScope)
    }

    private fun moveToTopAccuracy(pillInfo: ImageSearchModel) {
        val intent = Intent(this, ImageSearchTopAccuracyActivity::class.java)
        intent.putExtra(
            TOP_ACCURACY,
            ImageSearchModel(
                pillInfo.imageLink,
                pillInfo.pillItemSequence,
                pillInfo.pillName,
                pillInfo.shape
            )
        )
        startActivity(intent)
    }

    companion object {
        const val TOP_ACCURACY = "topAccuracy"
    }
}
