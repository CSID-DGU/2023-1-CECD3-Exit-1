package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.DURDataSource
import com.cecd.exitmed.data.model.response.ResponseDURSeniorCaution
import com.cecd.exitmed.domain.repository.DURRepository
import javax.inject.Inject

class DURRepositoryImpl @Inject constructor(
    private val durDataSource: DURDataSource
) : DURRepository {
    override suspend fun fetchDURSeniorCaution(itemSeq: String): Result<List<ResponseDURSeniorCaution.Body.Item>> =
        runCatching {
            durDataSource.fetchSeniorCaution(itemSeq = itemSeq).body.items
        }
}
