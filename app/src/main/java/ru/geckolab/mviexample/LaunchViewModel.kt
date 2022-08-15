package ru.geckolab.mviexample

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.geckolab.mviexample.commons_android.base.BaseViewModel
import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.navigation.Screens
import ru.geckolab.mviexample.navigation.root.RootTabsFragmentRouter
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val tabsFragmentRouter: RootTabsFragmentRouter
) : BaseViewModel() {

    init {
        tabsFragmentRouter.newRootTab(Screens.tabHost(RootPage.HOME))
    }

    fun onCreate() {}

    fun onTabSelected(rootPage: RootPage) {
        tabsFragmentRouter.navigateToTabs(Screens.tabHost(rootPage))
    }

    fun onTabReselected(rootPage: RootPage) {
        tabsFragmentRouter.reselectedTabs(rootPage)
    }
}