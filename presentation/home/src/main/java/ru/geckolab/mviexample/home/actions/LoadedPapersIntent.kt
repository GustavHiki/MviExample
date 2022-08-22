package ru.geckolab.mviexample.home.actions

import kotlinx.coroutines.CoroutineScope
import ru.geckolab.mviexample.home.HomeEvent
import ru.geckolab.mviexample.home.HomeStateView
import ru.geckolab.mviexample.home.databinding.FragmentHomeBinding
import ru.geckolab.mviexample.mvi_core.MviActionIntent
import ru.geckolab.mviexample.mvi_core.Store
import javax.inject.Inject

class LoadedPapersIntent @Inject constructor(): MviActionIntent<FragmentHomeBinding, HomeEvent.LoadedPapers>() {
    override fun execute(store: Store<*>, event: HomeEvent.LoadedPapers, coroutineScope: CoroutineScope) {
        super.execute(store, event, coroutineScope)
        store.updateState<HomeStateView> {
            it.copy(
                isLoading = false,
                text = event.papers.shuffled().first().title
            )
        }
    }
}