package com.makhalibagas.chucknorris.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText.getString() = this.text.toString().trim()

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.isVisible(state: Boolean) {
    if (state) this.visible() else this.gone()
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}