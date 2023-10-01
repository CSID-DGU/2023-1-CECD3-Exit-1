package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.domain.type.DoseTimeTable
import com.cecd.exitmed.domain.type.PillDrawerData

interface DoseRepository {
    suspend fun fetchPillDrawerList(): Result<List<PillDrawerData>>
    suspend fun fetchDoseTimeTable(): Result<List<DoseTimeTable>>
}
