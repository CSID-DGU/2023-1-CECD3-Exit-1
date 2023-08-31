package com.cecd.exitmed.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivitySignUpPasswordBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.extension.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpPasswordActivity :
    BindingActivity<ActivitySignUpPasswordBinding>(R.layout.activity_sign_up_password) {
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
    }

    private fun addListeners() {
        binding.layoutSignUpPassword.setOnClickListener {
            showKeyboard(it, false)
        }
        binding.btnNext.setOnClickListener {
            moveToSignUpInfo()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.ivClose.setOnClickListener {
            moveToHSignIn()
        }
    }

    private fun moveToSignUpInfo() {
        val email = intent.getStringExtra(EMAIL)
        val intent = Intent(this, SignUpInfoActivity::class.java)
        intent.putExtra(EMAIL, email)
        intent.putExtra(PASSWORD, viewModel.inputPW.value)
        startActivity(intent)
    }

    private fun moveToHSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}
