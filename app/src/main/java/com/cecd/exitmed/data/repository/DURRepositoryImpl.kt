package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.DURDataSource
import com.cecd.exitmed.data.model.response.ResponseDURCaution
import com.cecd.exitmed.domain.repository.DURRepository
import javax.inject.Inject

class DURRepositoryImpl @Inject constructor(
    private val durDataSource: DURDataSource
) : DURRepository {
    override suspend fun fetchDURAgeProhibition(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>> =
        runCatching {
            durDataSource.fetchAgeProhibition(itemSeq = itemSeq).body.items
        }

    override suspend fun fetchDURPregnantProhibition(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>> =
        runCatching {
            durDataSource.fetchPregnantProhibition(itemSeq = itemSeq).body.items
        }

    override suspend fun fetchDURCapacityCaution(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>> =
        runCatching {
            durDataSource.fetchCapacityCaution(itemSeq = itemSeq).body.items
        }

    override suspend fun fetchDURAdministrationDurationCaution(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>> =
        runCatching {
            durDataSource.fetchAdministrationDurationCaution(itemSeq = itemSeq).body.items
        }

    override suspend fun fetchDURSeniorCaution(itemSeq: String): Result<List<ResponseDURCaution.Body.Item>> =
        runCatching {
            durDataSource.fetchSeniorCaution(itemSeq = itemSeq).body.items
        }
}
