package com.cecd.exitmed.data.service

import com.cecd.exitmed.data.model.response.ResponseImageSearch
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageSearchService {
    @Multipart
    @POST("auth/search/image-search")
    suspend fun searchImagePill(
        @Part image: MultipartBody.Part,
    ): ResponseImageSearch
}
