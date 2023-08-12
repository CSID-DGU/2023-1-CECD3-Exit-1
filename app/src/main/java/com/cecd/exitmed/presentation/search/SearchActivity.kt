package com.cecd.exitmed.presentation.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivitySearchBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private val searchViewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this

        setSearchPagerAdapter()
    }

    private fun setSearchPagerAdapter() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(SearchHistoryFragment())
        fragmentList.add(SearchBookmarkFragment())

        val adapter = SearchPagerAdapter(fragmentList, this)
        binding.vpSearch.adapter = adapter

        TabLayoutMediator(binding.tabSearch, binding.vpSearch) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.search_history)
                1 -> tab.text = getString(R.string.search_bookmark)
            }
        }.attach()
    }
}
