package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.TextSearchDataSource
import com.cecd.exitmed.data.model.response.ResponseTextSearchBookmarkedList
import com.cecd.exitmed.domain.repository.TextSearchRepository
import com.cecd.exitmed.domain.type.Pill
import javax.inject.Inject

class TextSearchRepositoryImpl @Inject constructor(
    private val textSearchDataSource: TextSearchDataSource
) : TextSearchRepository {
    override suspend fun textPillSearch(textSearchTerm: String): Result<List<Pill>> =
        runCatching {
            textSearchDataSource.textPillSearch(textSearchTerm).toSearchPill()
        }

    override suspend fun fetchRecentSearchTerm(): Result<List<String>> =
        runCatching {
            textSearchDataSource.fetchRecentSearchTerm().toRecentSearchTermString()
        }

    override suspend fun fetchTextSearchBookmarkedList(): Result<List<ResponseTextSearchBookmarkedList.Data>> =
        runCatching {
            textSearchDataSource.fetchTextSearchBookmarkedList().data
        }
}
