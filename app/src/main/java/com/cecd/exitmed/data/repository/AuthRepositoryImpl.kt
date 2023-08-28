package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.AuthDataSource
import com.cecd.exitmed.data.model.request.RequestEmailDoubleCheck
import com.cecd.exitmed.data.model.request.RequestSignUp
import com.cecd.exitmed.data.model.response.ResponseEmailDoubleCheck
import com.cecd.exitmed.data.model.response.ResponseSignUp
import com.cecd.exitmed.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun signUp(requestSignUp: RequestSignUp): Result<ResponseSignUp> =
        runCatching {
            authDataSource.signUp(requestSignUp)
        }

    override suspend fun checkEmailDuplicated(requestEmailDoubleCheck: RequestEmailDoubleCheck): Result<ResponseEmailDoubleCheck> =
        runCatching {
            authDataSource.checkEmailDuplicated(requestEmailDoubleCheck)
        }
}
