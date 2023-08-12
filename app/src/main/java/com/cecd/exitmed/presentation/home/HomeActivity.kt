package com.cecd.exitmed.presentation.home

import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityHomeBinding
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}