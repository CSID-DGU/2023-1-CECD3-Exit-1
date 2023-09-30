package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.request.RequestBookmark

interface BookmarkRepository {
    suspend fun bookmark(requestBookmark: RequestBookmark): Result<Boolean>
}
