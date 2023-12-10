package com.cecd.exitmed.data.repository

import com.cecd.exitmed.data.dataSource.remote.ImageSearchDataSource
import com.cecd.exitmed.data.model.response.ResponseImageSearch
import com.cecd.exitmed.domain.repository.ImageSearchRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageSearchRepositoryImpl @Inject constructor(
    private val imageSearchDataSource: ImageSearchDataSource
) : ImageSearchRepository {
    override suspend fun searchImagePill(image: MultipartBody.Part): Result<ResponseImageSearch> =
        runCatching {
            imageSearchDataSource.searchImagePill(image)
        }
}
