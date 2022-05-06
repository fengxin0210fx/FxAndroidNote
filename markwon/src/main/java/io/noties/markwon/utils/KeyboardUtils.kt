package io.noties.markwon.app.utils

import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi

object KeyboardUtils {

    @RequiresApi(Build.VERSION_CODES.M)
    fun show(view: View) {
        view.context.getSystemService(InputMethodManager::class.java)
                ?.showSoftInput(view, 0)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun hide(view: View) {
        view.context.getSystemService(InputMethodManager::class.java)
                ?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}