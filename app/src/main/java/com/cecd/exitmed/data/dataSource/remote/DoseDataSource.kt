package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.request.RequestPillCreation
import com.cecd.exitmed.data.model.response.ResponseDoseTimeTable
import com.cecd.exitmed.data.model.response.ResponseDrawerDetail
import com.cecd.exitmed.data.model.response.ResponsePillCreation
import com.cecd.exitmed.data.model.response.ResponsePillDrawerList
import com.cecd.exitmed.data.service.DoseService
import javax.inject.Inject

class DoseDataSource @Inject constructor(
    private val doseService: DoseService
) {
    suspend fun fetchPillDrawerList(): ResponsePillDrawerList =
        doseService.fetchPillDrawerList()

    suspend fun fetchDoseTimeTable(): ResponseDoseTimeTable =
        doseService.fetchDoseTimeTable()

    suspend fun addToPillDrawer(requestPillCreation: RequestPillCreation): ResponsePillCreation =
        doseService.addToPillDrawer(requestPillCreation)

    suspend fun fetchDrawerPillDetail(itemSeq: Int): ResponseDrawerDetail =
        doseService.fetchDrawerPillDetail(itemSeq)
}
