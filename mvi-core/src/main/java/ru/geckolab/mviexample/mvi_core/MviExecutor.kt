package ru.geckolab.mviexample.mvi_core

import kotlinx.coroutines.CoroutineScope

interface MviExecutor<Event: MviEvent> {
    fun execute(store: Store<*>, event: Event, coroutineScope: CoroutineScope) {}
}