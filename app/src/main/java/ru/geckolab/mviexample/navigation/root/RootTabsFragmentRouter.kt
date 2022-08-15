package ru.geckolab.mviexample.navigation.root

import com.github.terrakok.cicerone.Router
import ru.geckolab.mviexample.commons_android.models.FragmentAnim
import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.navigation.ForwardTab
import ru.geckolab.mviexample.navigation.NewTabRootScreen

typealias TabScreenChangeListener = (TabFragmentScreen) -> Unit
typealias TabScreenReselectedListener = (RootPage) -> Unit

class RootTabsFragmentRouter : Router() {
    private val tabScreenChangeListeners = mutableListOf<TabScreenChangeListener>()
    private val tabScreenReselectedListeners = mutableListOf<TabScreenReselectedListener>()
    private var rootTabScreen: TabFragmentScreen? = null
    private var currentTabScreen: TabFragmentScreen? = null

    fun newRootTab(screen: TabFragmentScreen) {
        executeCommands(NewTabRootScreen(screen))
        rootTabScreen = screen
        currentTabScreen = rootTabScreen
    }

    fun reselectedTabs(rootPage: RootPage) {
        tabScreenReselectedListeners.forEach { it.invoke(rootPage) }
    }

    fun navigateToTabs(screen: TabFragmentScreen) {
        screen.fragmentAnim = getAnimationByTabScreen(screen)

        executeCommands(ForwardTab(screen))
        tabScreenChangeListeners.forEach { it.invoke(screen) }
        currentTabScreen = screen
    }

    fun exitTab() {
        exit()
        rootTabScreen?.let { rootTab ->
            tabScreenChangeListeners.forEach { it.invoke(rootTab) }
        }
    }

    fun addTabScreenReselectedListener(tabScreenReselectedListener: TabScreenReselectedListener) =
        tabScreenReselectedListeners.add(tabScreenReselectedListener)

    fun removeTabScreenReselectedListener(tabScreenReselectedListener: TabScreenReselectedListener) =
        tabScreenReselectedListeners.remove(tabScreenReselectedListener)

    private fun getAnimationByTabScreen(screen: TabFragmentScreen): FragmentAnim {
        val currentScreen = currentTabScreen ?: throw IllegalArgumentException("First call 'newRootTab(screen)'")
        return if (screen.rootPage.ordinal > currentScreen.rootPage.ordinal) {
            FragmentAnim.SHARED_X_AXIS_LEFT
        } else {
            FragmentAnim.SHARED_X_AXIS_RIGHT
        }
    }
}