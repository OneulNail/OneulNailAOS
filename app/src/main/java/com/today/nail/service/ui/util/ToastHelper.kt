package com.today.nail.service.ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

@SuppressLint("StaticFieldLeak")
object ToastHelper {

    var context : Context? = null

    fun initContext(context: Context) {
        this.context = context
    }

    fun showToast(msg : String) {
        context?.let {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun disposeContext() {
        context = null
    }
}