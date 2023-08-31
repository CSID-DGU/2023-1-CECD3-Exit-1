package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.request.RequestEmailDoubleCheck
import com.cecd.exitmed.data.model.request.RequestSignIn
import com.cecd.exitmed.data.model.request.RequestSignUp
import com.cecd.exitmed.data.model.response.ResponseEmailDoubleCheck
import com.cecd.exitmed.data.model.response.ResponseSignIn
import com.cecd.exitmed.data.model.response.ResponseSignUp
import com.cecd.exitmed.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun signUp(requestSignUp: RequestSignUp): ResponseSignUp =
        authService.signUp(requestSignUp)

    suspend fun checkEmailDuplicated(requestEmailDoubleCheck: RequestEmailDoubleCheck): ResponseEmailDoubleCheck =
        authService.checkEmailDuplicated(requestEmailDoubleCheck)

    suspend fun signIn(requestSignIn: RequestSignIn): ResponseSignIn =
        authService.signIn(requestSignIn)
}
