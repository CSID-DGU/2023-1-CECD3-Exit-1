package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.response.ResponseSearchList
import com.cecd.exitmed.data.service.MyService
import javax.inject.Inject

class MyDataSource @Inject constructor(
    private val myService: MyService
) {
    suspend fun fetchMyBookmarkedList(): ResponseSearchList =
        myService.fetchMyBookMarkedList()
}
