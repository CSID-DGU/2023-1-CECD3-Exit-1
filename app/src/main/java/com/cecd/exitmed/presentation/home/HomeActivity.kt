package com.cecd.exitmed.presentation.home

import android.content.Intent
import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityHomeBinding
import com.cecd.exitmed.presentation.search.SearchActivity
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addListeners()
    }

    private fun addListeners() {
        binding.layoutHomeSearchBox.setOnClickListener {
            moveToSearch()
        }
    }

    private fun moveToSearch() {
        startActivity(Intent(this, SearchActivity::class.java))
    }
}
