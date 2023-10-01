package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.response.ResponsePillDrawerList
import com.cecd.exitmed.data.service.DoseyService
import javax.inject.Inject

class DoseDataSource @Inject constructor(
    private val doseyService: DoseyService
) {
    suspend fun fetchPillDrawerList(): ResponsePillDrawerList =
        doseyService.fetchPillDrawerList()
}
