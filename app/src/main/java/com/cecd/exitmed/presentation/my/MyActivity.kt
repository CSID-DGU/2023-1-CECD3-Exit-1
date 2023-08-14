package com.cecd.exitmed.presentation.my

import android.content.Intent
import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityMyBinding
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyActivity : BindingActivity<ActivityMyBinding>(R.layout.activity_my) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addListeners()
    }

    private fun addListeners() {
        binding.layoutMyPageBookmark.setOnClickListener {
            moveToPillBookMark()
        }
        binding.layoutToolbar.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun moveToPillBookMark() {
        startActivity(Intent(this, MyPillListActivity::class.java))
    }
}
