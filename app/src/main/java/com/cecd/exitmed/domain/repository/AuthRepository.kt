package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.request.RequestEmailDoubleCheck
import com.cecd.exitmed.data.model.request.RequestSignIn
import com.cecd.exitmed.data.model.request.RequestSignUp
import com.cecd.exitmed.data.model.response.ResponseEmailDoubleCheck
import com.cecd.exitmed.data.model.response.ResponseSignIn
import com.cecd.exitmed.data.model.response.ResponseSignUp

interface AuthRepository {
    suspend fun signUp(requestSignUp: RequestSignUp): Result<ResponseSignUp>

    suspend fun checkEmailDuplicated(requestEmailDoubleCheck: RequestEmailDoubleCheck): Result<ResponseEmailDoubleCheck>

    suspend fun signIn(requestSignIn: RequestSignIn): Result<ResponseSignIn>
}
