package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.request.RequestBookmark
import com.cecd.exitmed.data.model.response.ResponseBookmark
import com.cecd.exitmed.data.service.BookmarkService
import javax.inject.Inject

class BookmarkDataSource @Inject constructor(
    private val bookMarkService: BookmarkService
) {
    suspend fun bookmark(requestBookmark: RequestBookmark): ResponseBookmark =
        bookMarkService.bookmark(requestBookmark)
}
