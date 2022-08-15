package ru.geckolab.mviexample.commons_kotlin.delegates

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

@Suppress("FunctionName")
fun <T> SingleSharedFlow(): MutableSharedFlow<T> =
    MutableSharedFlow(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

@Suppress("FunctionName")
fun <T> OnceLatestSharedFlow(): MutableSharedFlow<T> =
    MutableSharedFlow(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

@Suppress("FunctionName")
fun <T> OnceFlow(): MutableSharedFlow<T> =
    MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
