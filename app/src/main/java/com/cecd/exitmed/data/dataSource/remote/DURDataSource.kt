package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.response.ResponseDURCaution
import com.cecd.exitmed.data.service.DURService
import javax.inject.Inject

class DURDataSource @Inject constructor(
    private val durService: DURService
) {
    suspend fun fetchAgeProhibition(
        itemSeq: String
    ): ResponseDURCaution =
        durService.fetchAgeProhibition(itemSeq = itemSeq)

    suspend fun fetchPregnantProhibition(
        itemSeq: String
    ): ResponseDURCaution =
        durService.fetchPregnantProhibition(itemSeq = itemSeq)

    suspend fun fetchCapacityCaution(
        itemSeq: String
    ): ResponseDURCaution =
        durService.fetchCapacityCaution(itemSeq = itemSeq)

    suspend fun fetchSeniorCaution(
        itemSeq: String
    ): ResponseDURCaution =
        durService.fetchSeniorCaution(itemSeq = itemSeq)
}
