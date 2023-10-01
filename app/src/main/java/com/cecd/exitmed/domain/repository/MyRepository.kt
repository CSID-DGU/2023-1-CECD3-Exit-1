package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.domain.type.PillDrawerData

interface MyRepository {
    suspend fun fetchPillDrawerList(): Result<List<PillDrawerData>>
}