package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.MyDataSource
import com.cecd.exitmed.domain.repository.MyRepository
import com.cecd.exitmed.domain.type.Pill
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myDataSource: MyDataSource
) : MyRepository {
    override suspend fun fetchMyBookmarkedList(): Result<List<Pill>> =
        runCatching {
            myDataSource.fetchMyBookmarkedList().toSearchPill()
        }
}
