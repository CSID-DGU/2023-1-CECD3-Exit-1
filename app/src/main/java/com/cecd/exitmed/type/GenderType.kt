package com.cecd.exitmed.type

import androidx.annotation.StringRes
import com.cecd.exitmed.R

enum class GenderType(
    @StringRes val gender: Int, val genderRequest: String
) {
    MALE(R.string.sign_up_info_gender_male, "male"), FEMALE(
        R.string.sign_up_info_gender_female, "female"
    ),
    NONE(R.string.sign_up_info_gender_none, "none")
}
