package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.response.ResponseRecentSearchTerm
import com.cecd.exitmed.domain.type.SearchPill

interface TextSearchRepository {
    suspend fun textPillSearch(textSearchTerm: String): Result<List<SearchPill>>
    suspend fun fetchRecentSearchTerm(): Result<List<String>>
}
