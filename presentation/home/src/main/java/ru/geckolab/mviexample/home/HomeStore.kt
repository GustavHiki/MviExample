package ru.geckolab.mviexample.home

import ru.geckolab.mviexample.home.actions.LoadPapersIntent
import ru.geckolab.mviexample.home.actions.LoadedPapersIntent
import ru.geckolab.mviexample.home.actions.LoadingIntent
import ru.geckolab.mviexample.home.actions.SnackBarIntent
import ru.geckolab.mviexample.mvi_core.Store
import javax.inject.Inject


class HomeStore @Inject constructor(
    private val snackBarIntent: SnackBarIntent,
    private val loadPapersIntent: LoadPapersIntent,
    private val loadingIntent: LoadingIntent,
    private val loadedPapersIntent: LoadedPapersIntent,
): Store<HomeEvent>(
    HomeStateView(
        isLoading = false,
        text = "wtf"
    )
) {
    override fun reduce(event: HomeEvent) {
        val intent = when (event) {
            is HomeEvent.SnackBarClick -> snackBarIntent
            is HomeEvent.LoadPapers -> loadPapersIntent
            is HomeEvent.Loading -> loadingIntent
            is HomeEvent.LoadedPapers -> loadedPapersIntent
        }
        emitIntent(intent, event)
    }
}