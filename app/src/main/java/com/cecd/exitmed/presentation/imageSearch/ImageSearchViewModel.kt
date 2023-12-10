package com.cecd.exitmed.presentation.imageSearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.data.model.response.ResponseImageSearch
import com.cecd.exitmed.domain.repository.ImageSearchRepository
import com.cecd.exitmed.util.ContentUriRequestBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(
    private val imageSearchRepository: ImageSearchRepository
) : ViewModel() {
    private val _image = MutableStateFlow<ContentUriRequestBody?>(null)
    val image get() = _image.asStateFlow()
    private val _topAccuracyImageInfo = MutableStateFlow<ResponseImageSearch?>(null)
    val topAccuracyImageInfo get() = _topAccuracyImageInfo.asStateFlow()

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun searchImagePill() {
        if (image.value != null) {
            viewModelScope.launch {
                image.value?.toFormData()?.let {
                    imageSearchRepository.searchImagePill(it)
                        .onSuccess { imageInfo ->
                            _topAccuracyImageInfo.value = imageInfo
                        }.onFailure { throwable ->
                            Timber.e(throwable.message)
                        }
                }
            }
        }
    }
}