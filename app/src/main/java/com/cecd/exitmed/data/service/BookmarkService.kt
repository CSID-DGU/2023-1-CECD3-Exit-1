package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.request.RequestBookmark
import com.cecd.exitmed.data.model.response.ResponseBookmark
import retrofit2.http.Body
import retrofit2.http.POST

interface BookmarkService {
    @POST("auth/search/favorite")
    suspend fun bookmark(
        @Body requestBookmark: RequestBookmark
    ): ResponseBookmark
}
