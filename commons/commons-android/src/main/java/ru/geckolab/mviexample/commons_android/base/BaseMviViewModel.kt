package ru.geckolab.mviexample.commons_android.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.onEach
import ru.geckolab.mviexample.mvi_core.MviEvent
import ru.geckolab.mviexample.mvi_core.Store

abstract class BaseMviViewModel<Event : MviEvent>(
    private val store: Store<Event>
) : BaseViewModel() {

    init {
        store.executor
            .onEach { (executor, event) -> executor.execute(store, event, viewModelScope) }
            .inViewModel()
    }

    val state = store.state
    val action = store.action

    fun reduce(event: Event) {
        store.reduce(event)
    }
}