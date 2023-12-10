package com.cecd.exitmed.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageSearchModel(
    val imageLink: String,
    val pillItemSequence: Int,
    val pillName: String,
    val shape: String
): Parcelable