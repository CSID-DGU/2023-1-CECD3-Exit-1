package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.response.ResponseRecentSearchTerm
import com.cecd.exitmed.data.model.response.ResponseTextSearchList
import com.cecd.exitmed.data.service.TextSearchService
import javax.inject.Inject

class TextSearchDataSource @Inject constructor(
    private val textSearchService: TextSearchService
) {
    suspend fun textPillSearch(textSearchTerm: String): ResponseTextSearchList =
        textSearchService.textPillSearch(textSearchTerm)

    suspend fun fetchRecentSearchTerm(): ResponseRecentSearchTerm =
        textSearchService.fetchRecentSearchTerm()
}
