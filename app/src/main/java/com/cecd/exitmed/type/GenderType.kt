package com.cecd.exitmed.type

import androidx.annotation.StringRes
import com.cecd.exitmed.R

enum class GenderType(@StringRes val gender: Int) {
    MALE(R.string.sign_up_info_gender_male), FEMALE(R.string.sign_up_info_gender_female), NONE(R.string.sign_up_info_gender_none)
}