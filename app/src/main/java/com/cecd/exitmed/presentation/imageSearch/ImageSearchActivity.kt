package com.cecd.exitmed.presentation.imageSearch

import android.content.Intent
import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityImageSearchBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.github.dhaval2404.imagepicker.ImagePicker

class ImageSearchActivity :
    BindingActivity<ActivityImageSearchBinding>(R.layout.activity_image_search) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addListeners()
    }

    private fun addListeners() {
        binding.btnImageSearch.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .cropSquare()
                .start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            binding.ivImageSearchPill.setImageURI(it.data)
        }
    }
}
