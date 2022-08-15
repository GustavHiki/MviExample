package ru.geckolab.mviexample.commons_kotlin.ext

import kotlinx.coroutines.flow.MutableStateFlow


fun <T> MutableStateFlow<T>.fresh(function: (T) -> T) {
    tryEmit(function(value))
}