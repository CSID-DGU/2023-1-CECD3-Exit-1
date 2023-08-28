package com.cecd.exitmed.presentation.auth

import android.content.Intent
import android.os.Bundle
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivitySignInBinding
import com.cecd.exitmed.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addListeners()
    }

    private fun addListeners() {
        binding.tvSignUp.setOnClickListener {
            moveToSignUp()
        }
    }

    private fun moveToSignUp() {
        startActivity(Intent(this, SignUpEmailActivity::class.java))
    }
}
