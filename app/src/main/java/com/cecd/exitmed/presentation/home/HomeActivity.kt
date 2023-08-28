package com.cecd.exitmed.presentation.home

import android.content.Intent
import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityHomeBinding
import com.cecd.exitmed.presentation.my.MyActivity
import com.cecd.exitmed.presentation.textSearch.SearchActivity
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
        binding.ivHomeMyPage.setOnClickListener {
            moveToMyPage()
        }
    }

    private fun moveToSearch() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    private fun moveToMyPage() {
        startActivity(Intent(this, MyActivity::class.java))
    }
}
