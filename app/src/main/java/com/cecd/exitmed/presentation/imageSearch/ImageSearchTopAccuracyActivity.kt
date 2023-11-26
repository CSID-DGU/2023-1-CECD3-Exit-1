package com.cecd.exitmed.presentation.imageSearch

import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityImageSearchTopAccuracyBinding
import com.cecd.exitmed.presentation.model.ImageSearchModel
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.binding.setImage
import com.cecd.exitmed.util.extension.parcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageSearchTopAccuracyActivity :
    BindingActivity<ActivityImageSearchTopAccuracyBinding>(R.layout.activity_image_search_top_accuracy) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageSearchInfo = intent.parcelable<ImageSearchModel>(TOP_ACCURACY)
        imageSearchInfo?.let { imageSearchInfo ->
            binding.ivTopAccuracyImage.setImage(imageSearchInfo.imageLink)
            binding.tvTopAccuracyPillName.text = String.format("제품명: %s", imageSearchInfo.pillName)
            binding.tvTopAccuracyPillShpae.text = String.format("형상: %s", imageSearchInfo.shape)
        }
    }

    companion object {
        const val TOP_ACCURACY = "topAccuracy"
    }
}
