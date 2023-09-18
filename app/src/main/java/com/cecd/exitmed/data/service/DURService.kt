package com.cecd.exitmed.data.service

import com.cecd.exitmed.BuildConfig
import com.cecd.exitmed.data.model.response.ResponseDURCaution
import retrofit2.http.GET
import retrofit2.http.Query

interface DURService {
    // 연령 금기
    @GET("getSpcifyAgrdeTabooInfoList03")
    suspend fun fetchAgeProhibition(
        @Query("serviceKey") serviceKey: String = BuildConfig.DUR_DECODE_KEY,
        @Query("type") type: String = "json",
        @Query("itemSeq") itemSeq: String
    ): ResponseDURCaution

    // 임부금기
    @GET("getPwnmTabooInfoList03")
    suspend fun fetchPregnantProhibition(
        @Query("serviceKey") serviceKey: String = BuildConfig.DUR_DECODE_KEY,
        @Query("type") type: String = "json",
        @Query("itemSeq") itemSeq: String
    ): ResponseDURCaution

    // 용량주의
    @GET("getCpctyAtentInfoList03")
    suspend fun fetchCapacityCaution(
        @Query("serviceKey") serviceKey: String = BuildConfig.DUR_DECODE_KEY,
        @Query("type") type: String = "json",
        @Query("itemSeq") itemSeq: String
    ): ResponseDURCaution

    // 투여 기간 주의
    @GET("getMdctnPdAtentInfoList03")
    suspend fun fetchAdministrationDurationCaution(
        @Query("serviceKey") serviceKey: String = BuildConfig.DUR_DECODE_KEY,
        @Query("type") type: String = "json",
        @Query("itemSeq") itemSeq: String
    ): ResponseDURCaution

    // 노인 주의
    @GET("getOdsnAtentInfoList03")
    suspend fun fetchSeniorCaution(
        @Query("serviceKey") serviceKey: String = BuildConfig.DUR_DECODE_KEY,
        @Query("type") type: String = "json",
        @Query("itemSeq") itemSeq: String
    ): ResponseDURCaution
}
