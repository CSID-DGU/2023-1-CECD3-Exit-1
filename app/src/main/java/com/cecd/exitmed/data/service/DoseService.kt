package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.request.RequestPillCreation
import com.cecd.exitmed.data.model.response.ResponseDoseTimeTable
import com.cecd.exitmed.data.model.response.ResponseOnOffDoseAlarm
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

    @GET("auth/pill/toggle-alarm/{item_sequence}")
    suspend fun onOffDoseAlarm(
        @Path("item_sequence") itemSequence: Int,
    ): ResponseOnOffDoseAlarm

    @GET("auth/pill/drawer/{item_sequence}")
    suspend fun fetchDrawerPillDetail(
        @Path("item_sequence") itemSequence: Int,
    ): ResponseDrawerDetail
}
