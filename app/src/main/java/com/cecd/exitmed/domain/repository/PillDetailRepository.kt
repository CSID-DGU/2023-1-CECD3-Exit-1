package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.response.ResponsePillDetail

interface PillDetailRepository {
    suspend fun fetchPillDetail(itemSeq: Int): Result<ResponsePillDetail>
}
