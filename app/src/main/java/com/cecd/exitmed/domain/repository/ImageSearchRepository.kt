package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.data.model.response.ResponseImageSearch
import okhttp3.MultipartBody

interface ImageSearchRepository {
    suspend fun searchImagePill(image: MultipartBody.Part): Result<ResponseImageSearch>
}
