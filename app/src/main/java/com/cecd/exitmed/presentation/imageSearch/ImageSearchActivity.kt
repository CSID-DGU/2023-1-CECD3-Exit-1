package com.cecd.exitmed.presentation.imageSearch

import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityImageSearchBinding
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageSearchActivity :
    BindingActivity<ActivityImageSearchBinding>(R.layout.activity_image_search) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_image_search_result, ImageSearchFragment())
                .commit()
        }
    }
}
