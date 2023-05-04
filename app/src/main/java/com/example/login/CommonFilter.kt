package com.example.login

import android.text.InputFilter
import android.text.Spanned

class CommonFilter  : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {
        return source.replace(Regex("[^0-9a-zA-Z]"), "")
    }
}