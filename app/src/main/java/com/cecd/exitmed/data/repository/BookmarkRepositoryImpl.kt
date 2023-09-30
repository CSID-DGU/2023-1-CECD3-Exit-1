package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.BookmarkDataSource
import com.cecd.exitmed.data.model.request.RequestBookmark
import com.cecd.exitmed.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDataSource: BookmarkDataSource
) : BookmarkRepository {
    override suspend fun bookmark(requestBookmark: RequestBookmark): Result<Boolean> =
        runCatching {
            bookmarkDataSource.bookmark(requestBookmark).bookMarked
        }
}
