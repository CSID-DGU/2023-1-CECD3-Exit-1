package com.cecd.exitmed.data.service

import com.cecd.exitmed.BuildConfig
import com.cecd.exitmed.data.model.response.ResponseDURCaution
import retrofit2.http.GET
import retrofit2.http.Query

interface DURService {
    @GET("getSpcifyAgrdeTabooInfoList03")
    suspend fun fetchAgeProhibition(
        @Query("serviceKey") serviceKey: String = BuildConfig.DUR_DECODE_KEY,
        @Query("type") type: String = "json",
        @Query("itemSeq") itemSeq: String
    ): ResponseDURCaution

    @GET("getPwnmTabooInfoList03")
    suspend fun fetchPregnantProhibition(
        @Query("serviceKey") serviceKey: String = BuildConfig.DUR_DECODE_KEY,
        @Query("type") type: String = "json",
        @Query("itemSeq") itemSeq: String
    ): ResponseDURCaution

    @GET("getOdsnAtentInfoList03")
    suspend fun fetchSeniorCaution(
        @Query("serviceKey") serviceKey: String = BuildConfig.DUR_DECODE_KEY,
        @Query("type") type: String = "json",
        @Query("itemSeq") itemSeq: String
    ): ResponseDURCaution

}
