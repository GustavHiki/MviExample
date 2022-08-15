package ru.geckolab.mviexample.host

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.geckolab.mviexample.commons_android.base.BaseViewModel
import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.navigation.Screens
import ru.geckolab.mviexample.navigation.child.ChildScreenRouters
import ru.geckolab.mviexample.navigation.root.RootTabsFragmentRouter
import ru.geckolab.mviexample.navigation.root.TabScreenReselectedListener

class HostViewModel @AssistedInject constructor(
    childScreenRouters: ChildScreenRouters,
    private val tabsFragmentRouter: RootTabsFragmentRouter,
    @Assisted private val currentRootPage: RootPage
) : BaseViewModel() {

    private val tabScreenReselectListener = object : TabScreenReselectedListener {
        override fun invoke(rootPage: RootPage) {
            if (currentRootPage == rootPage) {
                childScreenRouters[rootPage].backToRoot()
            }
        }
    }

    init {
        val rootFragmentScreen = when (currentRootPage) {
            RootPage.HOME -> Screens.home(currentRootPage)
        }

        childScreenRouters[currentRootPage].newRootScreen(rootFragmentScreen)

        tabsFragmentRouter.addTabScreenReselectedListener(tabScreenReselectListener)
    }

    fun onCreate() {}

    override fun onCleared() {
        super.onCleared()
        tabsFragmentRouter.removeTabScreenReselectedListener(tabScreenReselectListener)
    }
}