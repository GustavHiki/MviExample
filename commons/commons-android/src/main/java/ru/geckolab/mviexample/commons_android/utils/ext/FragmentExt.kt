package ru.geckolab.mviexample.commons_android.utils.ext

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment

fun Fragment.dimToPx(@DimenRes dimenReId: Int) = resources.getDimensionPixelSize(dimenReId)

fun Fragment.getColorCompat(@ColorRes colorId: Int) = requireContext().getColorCompat(colorId)

fun Fragment.drawableCompat(@DrawableRes drawableResId: Int): Drawable? =
    requireContext().drawableCompat(drawableResId)

fun Fragment.waitForTransition(targetView: View) {
    postponeEnterTransition()
    targetView.doOnPreDraw { startPostponedEnterTransition() }
}