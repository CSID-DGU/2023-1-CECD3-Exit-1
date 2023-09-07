package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.response.ResponseDURSeniorCaution

interface DURRepository {
    suspend fun fetchDURSeniorCaution(itemSeq: String): Result<List<ResponseDURSeniorCaution.Body.Item>>
}
