package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.response.ResponsePillDrawerList
import retrofit2.http.GET

interface MyService {
    @GET("auth/pill/drawer")
    suspend fun fetchPillDrawerList(): ResponsePillDrawerList
}
