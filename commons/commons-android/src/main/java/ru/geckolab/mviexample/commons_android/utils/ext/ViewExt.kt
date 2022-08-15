package ru.geckolab.mviexample.commons_android.utils.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar


fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.getColorCompat(@ColorRes colorId: Int) = context.getColorCompat(colorId)

fun View.drawableCompat(@DrawableRes drawableResId: Int): Drawable? = context.drawableCompat(drawableResId)

fun View.dimToPx(@DimenRes dimenReId: Int) = context.dimToPx(dimenReId)

fun View.fadeIn(
    duration: Long = 400,
    startDelay: Long = 0,
    withStartAction: Runnable? = null
) {
    show()
    animate()
        .setStartDelay(startDelay)
        .alpha(1f)
        .setDuration(duration)
        .withStartAction(withStartAction)
        .start()
}

fun View.fadeOut(
    duration: Long = 400,
    startDelay: Long = 0,
    withEndAction: Runnable? = null
) {
    show()
    animate()
        .setStartDelay(startDelay)
        .alpha(0f)
        .setDuration(duration)
        .withEndAction(withEndAction)
        .start()
}


fun View.shortSnackBar(
    @StringRes msg: Int,
): Unit = Snackbar
    .make(this, msg, Snackbar.LENGTH_SHORT)
    .show()