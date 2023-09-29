package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.TextSearchDataSource
import com.cecd.exitmed.domain.repository.TextSearchRepository
import com.cecd.exitmed.domain.type.SearchPill
import javax.inject.Inject

class TextSearchRepositoryImpl @Inject constructor(
    private val textSearchDataSource: TextSearchDataSource
) : TextSearchRepository {
    override suspend fun textPillSearch(textSearchTerm: String): Result<List<SearchPill>> =
        runCatching {
            textSearchDataSource.textPillSearch(textSearchTerm).toSearchPill()
        }
}
