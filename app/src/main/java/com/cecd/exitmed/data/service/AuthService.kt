package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.request.RequestSignUp
import com.cecd.exitmed.data.model.response.ResponseSignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user/signup")
    suspend fun signUp(
        @Body requestSignUp: RequestSignUp
    ): ResponseSignUp
}
