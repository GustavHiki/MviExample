package ru.geckolab.mviexample.commons_android.utils.ext

import androidx.core.view.WindowInsetsCompat

val WindowInsetsCompat.navigationBarHeight: Int
    get() = getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

val WindowInsetsCompat.statusBarHeight: Int
    get() = getInsets(WindowInsetsCompat.Type.statusBars()).top
