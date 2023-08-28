package com.cecd.exitmed.data.dataSource

import com.cecd.exitmed.data.model.request.RequestSignUp
import com.cecd.exitmed.data.model.response.ResponseSignUp
import com.cecd.exitmed.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun signUp(requestSignUp: RequestSignUp): ResponseSignUp =
        authService.signUp(requestSignUp)
}
