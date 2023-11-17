package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.request.RequestPillCreation
import com.cecd.exitmed.domain.type.DoseTimeTable
import com.cecd.exitmed.domain.type.PillDrawerData

interface DoseRepository {
    suspend fun fetchPillDrawerList(): Result<List<PillDrawerData>>
    suspend fun fetchDoseTimeTable(): Result<List<DoseTimeTable>>
    suspend fun addToPillDrawer(requestPillCreation: RequestPillCreation): Result<Boolean>
    suspend fun onOffDoseAlarm(itemSeq: Int): Result<Boolean>
}
