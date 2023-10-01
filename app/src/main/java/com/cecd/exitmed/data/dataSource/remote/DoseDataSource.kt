package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.response.ResponseDoseTimeTable
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
}
