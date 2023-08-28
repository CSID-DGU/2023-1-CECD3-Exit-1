package com.cecd.exitmed.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivitySignUpEmailBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.extension.showKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignUpEmailActivity :
    BindingActivity<ActivitySignUpEmailBinding>(R.layout.activity_sign_up_email) {
    private val viewModel: SignViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.root.setOnClickListener {
            showKeyboard(it, false)
        }
        binding.btnNext.setOnClickListener {
            moveToSignUpPassword()
        }
        binding.ivClose.setOnClickListener {
            finish()
        }
    }

    private fun collectData() {
        viewModel.inputEmail.flowWithLifecycle(lifecycle).onEach {
            if (viewModel.isEmailDuplicated.value != null) {
                viewModel.initEmailDuplicated()
            }
        }.launchIn(lifecycleScope)
    }

    private fun moveToSignUpPassword() {
        val intent = Intent(this, SignUpPasswordActivity::class.java)
        intent.putExtra(EMAIL, viewModel.inputEmail.value)
        startActivity(intent)
    }

    companion object {
        const val EMAIL = "email"
    }
}
