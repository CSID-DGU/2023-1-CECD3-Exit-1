package com.cecd.exitmed.type

import androidx.annotation.StringRes
import com.cecd.exitmed.R

enum class PillPeriodType(@StringRes val titleRes: Int) {
    EVERY_DAY(R.string.pill_creation_everyday), FIXED_DAY(R.string.pill_creation_fixed_day)
}
