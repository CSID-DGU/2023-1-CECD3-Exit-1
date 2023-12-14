package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.response.ResponseSearchList
import retrofit2.http.GET

interface MyService {
    @GET("auth/user/favorite")
    suspend fun fetchMyBookMarkedList(): ResponseSearchList
}
