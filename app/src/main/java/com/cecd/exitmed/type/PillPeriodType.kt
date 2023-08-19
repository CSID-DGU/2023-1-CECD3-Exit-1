package com.cecd.exitmed.type

import androidx.annotation.StringRes
import com.cecd.exitmed.R

enum class PillPeriodType(@StringRes val titleRes: Int) {
    EVERYDAY(R.string.pill_creation_everyday), FIXEDDAY(R.string.pill_creation_fixed_day)
}
