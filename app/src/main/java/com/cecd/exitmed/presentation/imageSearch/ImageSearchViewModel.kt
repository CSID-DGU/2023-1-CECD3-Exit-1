package com.cecd.exitmed.presentation.imageSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cecd.exitmed.domain.repository.ImageSearchRepository
import com.cecd.exitmed.domain.type.ImageSearchInfo
import com.cecd.exitmed.util.ContentUriRequestBody
import com.cecd.exitmed.util.UiState
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
    private val _imageSearchState =
        MutableStateFlow<UiState<List<ImageSearchInfo>>>(UiState.Loading)
    val imageSearchState get() = _imageSearchState.asStateFlow()
    private val _imageSearchInfoLists = MutableStateFlow<List<ImageSearchInfo>>(listOf())
    val imageSearchInfoLists get() = _imageSearchInfoLists.asStateFlow()

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun searchImagePill() {
        viewModelScope.launch {
            image.value?.toFormData()?.let { image ->
                imageSearchRepository.searchImagePill(image)
                    .onSuccess {
                        _imageSearchInfoLists.value = it
                        _imageSearchState.value = UiState.Success(it)
                    }
                    .onFailure { throwable ->
                        _imageSearchState.value = UiState.Error(throwable.message)
                        Timber.e(throwable.message)
                    }
            }
        }
    }
}
