package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.domain.type.ImageSearchInfo
import okhttp3.MultipartBody

interface ImageSearchRepository {
    suspend fun searchImagePill(image: MultipartBody.Part): Result<List<ImageSearchInfo>>
}
