package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.request.RequestPillCreation
import com.cecd.exitmed.data.model.response.ResponseDoseTimeTable
import com.cecd.exitmed.data.model.response.ResponseDrawerDetail
import com.cecd.exitmed.data.model.response.ResponsePillCreation
import com.cecd.exitmed.data.model.response.ResponsePillDrawerList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DoseService {
    @GET("auth/pill/drawer")
    suspend fun fetchPillDrawerList(): ResponsePillDrawerList

    @GET("auth/pill/get-alarm")
    suspend fun fetchDoseTimeTable(): ResponseDoseTimeTable

    @POST("auth/pill/add-to-drawer")
    suspend fun addToPillDrawer(
        @Body requestPillCreation: RequestPillCreation
    ): ResponsePillCreation

    @GET("auth/pill/drawer/{item_sequence}")
    suspend fun fetchDrawerPillDetail(
        @Path("item_sequence") itemSequence: Int,
    ): ResponseDrawerDetail
}
