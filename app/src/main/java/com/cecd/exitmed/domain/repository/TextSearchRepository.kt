package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.response.ResponseTextSearchBookmarkedList
import com.cecd.exitmed.domain.type.Pill

interface TextSearchRepository {
    suspend fun textPillSearch(textSearchTerm: String): Result<List<Pill>>
    suspend fun fetchRecentSearchTerm(): Result<List<String>>
    suspend fun fetchTextSearchBookmarkedList(): Result<List<ResponseTextSearchBookmarkedList.Data>>
}
