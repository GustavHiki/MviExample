package ru.geckolab.mviexample.mvi_core

import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ru.geckolab.mviexample.commons_kotlin.delegates.OnceFlow
import ru.geckolab.mviexample.commons_kotlin.ext.fresh


abstract class Store<Event: MviEvent>(
    initialState: MviStateView<*>
) {
    private val _state = MutableStateFlow<MviStateView<ViewBinding>>(initialState as MviStateView<ViewBinding>)
    val state = _state.asStateFlow()

    private val _action = OnceFlow<Pair<MviActionView<ViewBinding, MviEvent>, MviEvent>>()
    val action = _action.asSharedFlow()

    private val mviExecutor: MutableSharedFlow<Pair<MviActionIntent<ViewBinding, Event>, MviEvent>> = OnceFlow()
    val executor: Flow<Pair<MviExecutor<Event>, Event>> = mviExecutor.asSharedFlow()
        .map { (intent, event) ->
            _action.tryEmit(Pair(intent as MviActionView<ViewBinding, MviEvent>, event))
            Pair(intent as MviExecutor<Event>, event as Event)
        }

    protected fun <VB: ViewBinding, ME: MviEvent> emitIntent(intent: MviActionIntent<VB, ME>, event: MviEvent) {
        mviExecutor.tryEmit(Pair(intent as MviActionIntent<ViewBinding, Event>, event))
    }

    fun <State> freshState(function: (State) -> State) {
        _state.fresh { function.invoke(it as State) as MviStateView<ViewBinding> }
    }

    fun <State> updateState(function: (State) -> State) {
        _state.update { function.invoke(it as State) as MviStateView<ViewBinding> }
    }

    abstract fun reduce(event: Event)
}