package com.cecd.exitmed.presentation.imageSearch

import android.content.Intent
import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityImageCaptureCautionBinding
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageCaptureCautionActivity :
    BindingActivity<ActivityImageCaptureCautionBinding>(R.layout.activity_image_capture_caution) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addListeners()
    }

    private fun addListeners() {
        binding.btnImageCapture.setOnClickListener {
            moveToImageSearch()
        }
    }

    private fun moveToImageSearch() {
        startActivity(Intent(this, ImageSearchActivity::class.java))
    }
}
