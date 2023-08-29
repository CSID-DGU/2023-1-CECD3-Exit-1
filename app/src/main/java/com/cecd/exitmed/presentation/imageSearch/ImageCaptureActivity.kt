package com.cecd.exitmed.presentation.imageSearch

import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityImageCaptureBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageCaptureActivity :
    BindingActivity<ActivityImageCaptureBinding>(R.layout.activity_image_capture) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addListeners()
    }

    private fun addListeners() {
        binding.btnImageCapture.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .cropSquare()
                .start()
        }
    }
}
