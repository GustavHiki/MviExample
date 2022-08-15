package ru.geckolab.mviexample.navigation.child

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import ru.geckolab.mviexample.commons_android.navigation.RootPage

class ChildScreenRouters {
    private val cicerones = HashMap<RootPage, Cicerone<ChildFragmentRouter>>()

    operator fun get(rootPage: RootPage): ChildFragmentRouter =
        cicerones.getOrPut(rootPage) {
            Cicerone.create(ChildFragmentRouter())
        }.router

    fun navigatorHolder(rootPage: RootPage): NavigatorHolder =
        cicerones.getOrPut(rootPage) {
            Cicerone.create(ChildFragmentRouter())
        }.getNavigatorHolder()
}