package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.response.ResponseDURSeniorCaution
import com.cecd.exitmed.data.service.DURService
import javax.inject.Inject

class DURDataSource @Inject constructor(
    private val durService: DURService
) {
    suspend fun fetchSeniorCaution(
        itemSeq: String
    ): ResponseDURSeniorCaution =
        durService.fetchSeniorCaution(itemSeq = itemSeq)
}
