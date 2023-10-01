package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.response.ResponseDoseTimeTable
import com.cecd.exitmed.data.model.response.ResponsePillDrawerList
import retrofit2.http.GET

interface DoseService {
    @GET("auth/pill/drawer")
    suspend fun fetchPillDrawerList(): ResponsePillDrawerList

    @GET("auth/pill/get-alarm")
    suspend fun fetchDoseTimeTable(): ResponseDoseTimeTable
}
