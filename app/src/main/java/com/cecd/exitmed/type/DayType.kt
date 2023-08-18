package com.cecd.exitmed.type

import androidx.annotation.StringRes
import com.cecd.exitmed.R

enum class DayType(@StringRes val titleRes: Int) {
    MONDAY(R.string.pill_creation_monday),
    TUESDAY(R.string.pill_creation_tuesday),
    WEDNESDAY(R.string.pill_creation_wednesday),
    THURSDAY(R.string.pill_creation_thursday),
    FRIDAY(R.string.pill_creation_friday),
    SATURDAY(R.string.pill_creation_saturday),
    SUNDAY(R.string.pill_creation_sunday)
}
