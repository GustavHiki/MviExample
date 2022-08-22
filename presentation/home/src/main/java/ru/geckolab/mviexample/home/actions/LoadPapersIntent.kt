package ru.geckolab.mviexample.home.actions

import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import ru.geckolab.mviexample.domain.usecases.HomeUseCase
import ru.geckolab.mviexample.home.HomeEvent
import ru.geckolab.mviexample.home.HomeStore
import ru.geckolab.mviexample.home.databinding.FragmentHomeBinding
import ru.geckolab.mviexample.mvi_core.MviActionIntent
import ru.geckolab.mviexample.mvi_core.Store
import javax.inject.Inject

class LoadPapersIntent @Inject constructor(
    private val homeUseCase: HomeUseCase
) : MviActionIntent<FragmentHomeBinding, HomeEvent.LoadPapers>() {

    override fun execute(store: Store<*>, event: HomeEvent.LoadPapers, coroutineScope: CoroutineScope) {
        super.execute(store, event, coroutineScope)
        require(store is HomeStore)

//        homeUseCase.loadPapers()
//            .onStart { store.freshState<HomeStateView> { it.copy(isLoading = true) } }
//            .onEach { papers ->
//                store.freshState<HomeStateView> {
//                    it.copy(
//                        isLoading = false,
//                        text = papers.shuffled().first().title
//                    )
//                }
//            }
//            .launchIn(coroutineScope)

        homeUseCase.loadPapers()
            .onStart { store.reduce(HomeEvent.Loading) }
            .onEach { paper -> store.reduce(HomeEvent.LoadedPapers(paper)) }
            .launchIn(coroutineScope)

    }

    override fun render(binding: FragmentHomeBinding, data: HomeEvent.LoadPapers) {
        super.render(binding, data)
        Toast.makeText(binding.root.context, "loaded", Toast.LENGTH_SHORT).show()
    }
}