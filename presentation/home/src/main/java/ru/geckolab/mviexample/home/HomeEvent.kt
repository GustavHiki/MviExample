package ru.geckolab.mviexample.home

import ru.geckolab.mviexample.domain.models.Paper
import ru.geckolab.mviexample.mvi_core.MviEvent

sealed interface HomeEvent: MviEvent {
    data class SnackBarClick(val text: String): HomeEvent
    object LoadPapers: HomeEvent
    object Loading: HomeEvent
    data class LoadedPapers(val papers: List<Paper>): HomeEvent
}
