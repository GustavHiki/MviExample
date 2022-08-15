package ru.geckolab.mviexample.navigation

import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.commons_android.navigation.ScreensRouter
import ru.geckolab.mviexample.navigation.child.ChildScreenRouters
import ru.geckolab.mviexample.navigation.root.RootTabsFragmentRouter
import javax.inject.Inject

class ScreensRouterImpl @Inject constructor(
    private val rootTabRouters: RootTabsFragmentRouter,
    private val childScreenRouters: ChildScreenRouters,
) : ScreensRouter {

    override fun exit(rootPage: RootPage) {
        childScreenRouters[rootPage].exit()
    }
}