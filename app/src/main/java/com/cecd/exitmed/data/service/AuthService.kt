package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.request.RequestEmailDoubleCheck
import com.cecd.exitmed.data.model.request.RequestSignUp
import com.cecd.exitmed.data.model.response.ResponseEmailDoubleCheck
import com.cecd.exitmed.data.model.response.ResponseSignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user/signup")
    suspend fun signUp(
        @Body requestSignUp: RequestSignUp
    ): ResponseSignUp

    @POST("user/signup/check-duplicated")
    suspend fun checkEmailDuplicated(
        @Body requestEmailDoubleCheck: RequestEmailDoubleCheck
    ): ResponseEmailDoubleCheck
}
