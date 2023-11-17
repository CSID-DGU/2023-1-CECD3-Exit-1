package com.cecd.exitmed.presentation.pillCreation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityPillCreationBinding
import com.cecd.exitmed.presentation.dose.DoseActivity
import com.cecd.exitmed.util.binding.BindingActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillCreationActivity :
    BindingActivity<ActivityPillCreationBinding>(R.layout.activity_pill_creation) {
    private val viewModel: PillCreationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addListeners()
        collectData()
        setPillCreationPagerAdapter()
    }

    private fun addListeners() {
        binding.btnNext.setOnClickListener {
            if (binding.vpPillCreation.currentItem == 3) {
                viewModel.addToPillDrawer(intent.getIntExtra(ITEM_SEQ, -1))
            } else
                binding.vpPillCreation.currentItem++
        }
        binding.btnBack.setOnClickListener {
            when (binding.vpPillCreation.currentItem) {
                0 -> {}
                else -> {
                    binding.vpPillCreation.currentItem--
                }
            }
        }
        binding.ivClose.setOnClickListener {
            finish()
        }
    }

    private fun collectData() {
        viewModel.pagePosition.flowWithLifecycle(lifecycle).onEach { position ->
            binding.btnNext.setText(if (position == 3) R.string.pill_creation else R.string.next)
            binding.tvPillCreationStep.text =
                String.format(getString(R.string.pill_creation_step), position + 1)
        }.launchIn(lifecycleScope)
        viewModel.isAdded.flowWithLifecycle(lifecycle).onEach { isAdded ->
            if (isAdded)
                moveToPillDrawerList()
        }.launchIn(lifecycleScope)
    }

    private fun moveToPillDrawerList() {
        startActivity(Intent(this, DoseActivity::class.java))
        finish()
    }

    private fun getPageChangeCallback() =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                viewModel.setPagePosition(position)
            }
        }

    private fun setPillCreationPagerAdapter() {
        binding.vpPillCreation.isUserInputEnabled = false

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(PillCreationLastDateFragment())
        fragmentList.add(PillCreationPeriodFragment())
        fragmentList.add(PillCreationTimeFragment())
        fragmentList.add(PillCreationMemoFragment())

        val adapter = PillCreationPagerAdapter(fragmentList, this)
        with(binding.vpPillCreation) {
            this.adapter = adapter
            this.registerOnPageChangeCallback(getPageChangeCallback())
        }

        TabLayoutMediator(
            binding.pillCreationTabIndicator, binding.vpPillCreation
        ) { _, _ -> }.attach()
    }

    companion object {
        const val ITEM_SEQ = "itemSeq"
    }
}
