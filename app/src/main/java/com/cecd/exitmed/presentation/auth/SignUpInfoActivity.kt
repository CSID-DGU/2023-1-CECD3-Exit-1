package com.cecd.exitmed.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivitySignUpInfoBinding
import com.cecd.exitmed.presentation.home.HomeActivity
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.extension.showKeyboard
import com.cecd.exitmed.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignUpInfoActivity :
    BindingActivity<ActivitySignUpInfoBinding>(R.layout.activity_sign_up_info) {
    private val viewModel: SignViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.layoutSignUpInfo.setOnClickListener {
            showKeyboard(it, false)
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.ivClose.setOnClickListener {
            moveToSignIn()
        }
        binding.btnSignUp.setOnClickListener {
            val email = intent.getStringExtra(EMAIL) ?: ""
            val password = intent.getStringExtra(PASSWORD) ?: ""
            viewModel.signUp(
                email,
                password,
                viewModel.inputBirth.value.toInt(),
                viewModel.inputName.value,
                viewModel.inputGender.value?.genderRequest ?: ""
            )
            showKeyboard(it, false)
        }
    }

    private fun collectData() {
        viewModel.isCompleteSignUp.flowWithLifecycle(lifecycle).onEach { isSignUp ->
            when (isSignUp) {
                true -> {
                    showToast(getString(R.string.sign_up_success_toast))
                    moveToHome()
                }

                false -> showToast(getString(R.string.sign_up_fail_toast))
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun moveToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun moveToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}
