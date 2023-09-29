package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.response.ResponseRecentSearchTerm
import com.cecd.exitmed.data.model.response.ResponseTextSearchList
import retrofit2.http.GET
import retrofit2.http.Path

interface TextSearchService {
    @GET("auth/search/text-search/{search_text}")
    suspend fun textPillSearch(
        @Path("search_text") searchText: String,
    ): ResponseTextSearchList

    @GET("auth/search/search-list")
    suspend fun fetchRecentSearchTerm(): ResponseRecentSearchTerm
}
