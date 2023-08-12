package com.cecd.exitmed.util.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean?) {
    if (isVisible == null) return
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("selected")
fun View.setSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}