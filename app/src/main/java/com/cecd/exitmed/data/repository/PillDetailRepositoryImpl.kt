package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.PillDetailDataSource
import com.cecd.exitmed.data.model.response.ResponsePillDetail
import com.cecd.exitmed.domain.repository.PillDetailRepository
import javax.inject.Inject

class PillDetailRepositoryImpl @Inject constructor(
    private val pillDetailDataSource: PillDetailDataSource
) : PillDetailRepository {
    override suspend fun fetchPillDetail(itemSeq: Int): Result<ResponsePillDetail> =
        runCatching {
            pillDetailDataSource.fetchPillDetail(itemSeq)
        }
}
