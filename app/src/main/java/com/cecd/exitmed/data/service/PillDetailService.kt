package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.response.ResponsePillDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface PillDetailService {
    @GET("auth/pill/pill-info/{item_sequence}")
    suspend fun fetchPillDetail(
        @Path("item_sequence") itemSequence: Int,
    ): ResponsePillDetail
}
