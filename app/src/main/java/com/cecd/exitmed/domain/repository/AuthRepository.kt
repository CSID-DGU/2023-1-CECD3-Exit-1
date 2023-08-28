package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.request.RequestSignUp
import com.cecd.exitmed.data.model.response.ResponseSignUp

interface AuthRepository {
    suspend fun signUp(requestSignUp: RequestSignUp): Result<ResponseSignUp>
}
