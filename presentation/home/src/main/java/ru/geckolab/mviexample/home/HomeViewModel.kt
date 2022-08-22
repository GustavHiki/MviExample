package ru.geckolab.mviexample.home

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.geckolab.mviexample.commons_android.base.BaseMviViewModel
import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.mvi_core.MviEvent
import ru.geckolab.mviexample.mvi_core.Store

class HomeViewModel @AssistedInject constructor(
    store: HomeStore,
    @Assisted private val rootPage: RootPage
) : BaseMviViewModel<MviEvent>(store as Store<MviEvent>) {

}