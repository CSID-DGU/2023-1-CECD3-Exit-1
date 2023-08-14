package com.cecd.exitmed.presentation.sign

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivitySignUpEmailBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.extension.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpEmailActivity :
    BindingActivity<ActivitySignUpEmailBinding>(R.layout.activity_sign_up_email) {
    private val viewModel: SignViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
    }

    private fun addListeners() {
        binding.root.setOnClickListener {
            showKeyboard(it, false)
        }
        binding.btnNext.setOnClickListener {
            moveToSignUpPassword()
        }
    }

    private fun moveToSignUpPassword() {
        startActivity(Intent(this, SignUpPasswordActivity::class.java))
    }
}
