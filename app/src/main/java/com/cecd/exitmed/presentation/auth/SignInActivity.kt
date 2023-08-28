package com.cecd.exitmed.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivitySignInBinding
import com.cecd.exitmed.presentation.home.HomeActivity
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val authViewModel: SignViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = authViewModel
        binding.lifecycleOwner = this

        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.tvSignUp.setOnClickListener {
            moveToSignUp()
        }
    }

    private fun collectData() {
        authViewModel.isCompleteSignIn.flowWithLifecycle(lifecycle).onEach { isCompleteSignIn ->
            when (isCompleteSignIn) {
                true -> {
                    moveToHome()
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun moveToSignUp() {
        startActivity(Intent(this, SignUpEmailActivity::class.java))
    }

    private fun moveToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}
