package com.lucky.luckyappchallenge.utils

import android.view.View

fun View.show(show: Boolean) {
    if (show) {
        show()
    } else {
        hide()
    }
}

fun View.showOrDisappear(show: Boolean) {
    if (show) {
        show()
    } else {
        disappear()
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.disappear() {
    this.visibility = View.INVISIBLE
}
