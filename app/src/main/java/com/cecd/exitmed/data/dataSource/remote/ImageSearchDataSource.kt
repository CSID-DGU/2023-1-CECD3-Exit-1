package com.cecd.exitmed.data.dataSource.remote

import com.cecd.exitmed.data.model.response.ResponseImageSearch
import com.cecd.exitmed.data.service.ImageSearchService
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageSearchDataSource @Inject constructor(
    private val imageSearchService: ImageSearchService
) {
    suspend fun searchImagePill(image: MultipartBody.Part): ResponseImageSearch =
        imageSearchService.searchImagePill(image)
}