package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.DoseDataSource
import com.cecd.exitmed.data.model.request.RequestPillCreation
import com.cecd.exitmed.data.model.response.toDrawerDetail
import com.cecd.exitmed.domain.repository.DoseRepository
import com.cecd.exitmed.domain.type.DoseTimeTable
import com.cecd.exitmed.domain.type.DrawerDetail
import com.cecd.exitmed.domain.type.PillDrawerData
import javax.inject.Inject

class DoseRepositoryImpl @Inject constructor(
    private val doseDataSource: DoseDataSource
) : DoseRepository {
    override suspend fun fetchPillDrawerList(): Result<List<PillDrawerData>> =
        runCatching {
            doseDataSource.fetchPillDrawerList().toPillDrawerData()
        }

    override suspend fun fetchDoseTimeTable(): Result<List<DoseTimeTable>> =
        runCatching {
            doseDataSource.fetchDoseTimeTable().toDoseTimeTable()
        }

    override suspend fun addToPillDrawer(requestPillCreation: RequestPillCreation): Result<Boolean> =
        kotlin.runCatching {
            doseDataSource.addToPillDrawer(requestPillCreation).added
        }

    override suspend fun fetchDrawerPillDetail(itemSeq: Int): Result<DrawerDetail> =
        runCatching {
            doseDataSource.fetchDrawerPillDetail(itemSeq).toDrawerDetail()
        }
}
