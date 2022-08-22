package ru.geckolab.mviexample.commons_android.utils.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

fun Context.getColorCompat(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)

fun Context.getDrawableCompat(@DrawableRes drawableId: Int): Drawable =
    AppCompatResources.getDrawable(this, drawableId)!!

fun Context.drawableCompat(@DrawableRes drawableResId: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResId)

fun Context.dimToPx(@DimenRes dimenReId: Int) = resources.getDimensionPixelSize(dimenReId)

val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

fun View.copyToClipBoard(text: String, @StringRes notificationText: Int) {
    val clipboard = ContextCompat.getSystemService(context, ClipboardManager::class.java)
    val clip = ClipData.newPlainText("mviExample", text)
    clipboard?.setPrimaryClip(clip)
    shortSnackBar(notificationText)
}