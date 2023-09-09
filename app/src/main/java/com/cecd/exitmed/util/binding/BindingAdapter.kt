package com.cecd.exitmed.util.binding

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.cecd.exitmed.R

@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean?) {
    if (isVisible == null) return
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("isGone")
fun View.setGone(isGone: Boolean?) {
    if (isGone == null) return
    this.visibility = if (isGone) View.VISIBLE else View.GONE
}

@BindingAdapter("selected")
fun View.setSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("coloredText")
fun TextView.setColoredText(text: String?) {
    text?.let {
        val pattern = "\\[(.*?)]".toRegex()
        val matches = pattern.findAll(text)

        val spannableStringBuilder = SpannableStringBuilder(text)

        for (match in matches) {
            val start = match.range.first
            val end = match.range.last + 1

            val color = R.color.orange_700
            val span = ForegroundColorSpan(context.getColor(color))
            spannableStringBuilder.setSpan(
                span,
                start + 1,
                end - 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannableStringBuilder.replace(start, start + 1, "")
            spannableStringBuilder.replace(end - 2, end - 1, "")
        }
        this.text = spannableStringBuilder
    }
}
