package ru.geckolab.mviexample.commons_android.utils.ext

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.media.AudioManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

fun Activity?.closeKeyboard() {
    this?.currentFocus?.let { view ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

val Activity.mediaVolume
    get() = (getSystemService(Context.AUDIO_SERVICE) as? AudioManager)
        ?.getStreamVolume(AudioManager.STREAM_MUSIC) ?: 0

fun FragmentActivity.hideKeyboard() = currentFocus?.hideKeyboard()

fun FragmentActivity.keepScreenOn(keep: Boolean) {
    if (keep) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    } else {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}

@Suppress("DEPRECATION")
val Activity.screenHeight
    get() = with(this) {
        val outMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val display = display
            display?.getRealMetrics(outMetrics)
        } else {
            val display = windowManager.defaultDisplay
            display.getMetrics(outMetrics)
        }
        outMetrics.heightPixels
    }

@Suppress("DEPRECATION")
val Activity.rotation: Int
    get() = with(this) {
        val rotation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display?.rotation
        } else {
            windowManager.defaultDisplay.rotation
        }
        rotation ?: 0
    }

fun Activity.isGestureNavigationBar(navigationBarHeight: Int, isLandscape: Boolean): Boolean {
    val height = if (isLandscape) screenWidth else screenHeight
    return navigationBarHeight.toFloat() / height <= 0.03
}

fun Activity.setSensorOrientationIfCan() {
    if (Settings.System.getInt(contentResolver, Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
        Handler(Looper.getMainLooper()).postDelayed({
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }, 1000)
    }
}

fun Activity.setScreenBrightness(value: Float) {
    val lp = window.attributes
    lp.screenBrightness = value
    window.attributes = lp
}

fun Activity.getScreenBrightnessInPercent(isSystem: Boolean = false): Int {
    return if (isSystem) {
        val systemBrightness = Settings.System.getInt(
            contentResolver,
            Settings.System.SCREEN_BRIGHTNESS
        ) / 255f

        (systemBrightness * 100).toInt()
    } else {
        (window.attributes.screenBrightness * 100).toInt()
    }
}

fun Activity.setLandscapeOrientation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

fun Activity.setDefaultOrientation() {
    requestedOrientation = if (Settings.System.getInt(contentResolver, Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
        ActivityInfo.SCREEN_ORIENTATION_SENSOR
    } else {
        ActivityInfo.SCREEN_ORIENTATION_USER
    }
}