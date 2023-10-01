package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.response.ResponsePillDetail
import com.cecd.exitmed.data.service.PillDetailService
import javax.inject.Inject

class PillDetailDataSource @Inject constructor(
    private val pillDetailService: PillDetailService
) {
    suspend fun fetchPillDetail(itemSeq: Int): ResponsePillDetail =
        pillDetailService.fetchPillDetail(itemSeq)
}
