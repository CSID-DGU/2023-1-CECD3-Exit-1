package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.response.ResponseDURCaution

interface DURRepository {
    suspend fun fetchDURAgeProhibition(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>>
    suspend fun fetchDURPregnantProhibition(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>>
    suspend fun fetchDURCapacityCaution(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>>
    suspend fun fetchDURAdministrationDurationCaution(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>>
    suspend fun fetchDURSeniorCaution(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>>
}
