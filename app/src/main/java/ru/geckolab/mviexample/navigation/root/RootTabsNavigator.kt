package ru.geckolab.mviexample.navigation.root

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.BackTo
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geckolab.mviexample.commons_android.models.FragmentAnim
import ru.geckolab.mviexample.commons_android.utils.ext.hideKeyboard
import ru.geckolab.mviexample.navigation.ForwardTab
import ru.geckolab.mviexample.navigation.NewTabRootScreen

open class RootTabsNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory
) : AppNavigator(activity, containerId, fragmentManager, fragmentFactory) {
    private var rootScreen: Screen? = null

    override fun applyCommands(commands: Array<out Command>) {
        activity.hideKeyboard()
        super.applyCommands(commands)
    }

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
        super.setupFragmentTransaction(screen, fragmentTransaction, currentFragment, nextFragment)
        if (screen is AnimFragmentScreen && screen.fragmentAnim != FragmentAnim.NONE) {
            fragmentTransaction.setCustomAnimations(
                screen.fragmentAnim.enterFragment,
                screen.fragmentAnim.exitFragment,
                screen.fragmentAnim.enterFragmentBack,
                screen.fragmentAnim.exitFragmentBack
            )
        }

        fragmentTransaction.setReorderingAllowed(true)
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is ForwardTab -> forwardTab(command)
            is NewTabRootScreen -> newTabRootScreen(command)
            else -> super.applyCommand(command)
        }
    }

    override fun back() {
        if (isLastNotRootScreen()) {
            backTo(BackTo(null))
//            rootScreen?.let { forwardTab(ForwardTab(it)) }
        } else {
            activityBack()
        }
    }

    protected open fun forwardTab(command: ForwardTab) {
        val screen = command.screen
        if (screen is TabFragmentScreen) {
            val restoredFragment = screen.restoreFragment(fragmentManager)
            if (restoredFragment == null) {
                commitFragmentScreen(screen.createFragment(fragmentFactory), screen, true)
            } else {
                commitFragmentScreen(restoredFragment, screen, false)
            }
        }
    }

    protected open fun newTabRootScreen(command: NewTabRootScreen) {
        backTo(BackTo(null))
        replace(Replace(command.screen))
        rootScreen = command.screen
    }

    protected open fun commitFragmentScreen(
        fragment: Fragment,
        screen: FragmentScreen,
        addToBackStack: Boolean // ignore
    ) {
        with(fragmentManager.beginTransaction()) {
            setReorderingAllowed(true)

            setupFragmentTransaction(
                screen,
                this,
                fragmentManager.findFragmentById(containerId),
                fragment
            )

            if (screen.clearContainer) {
                replace(containerId, fragment, screen.screenKey)
            } else {
                add(containerId, fragment, screen.screenKey)
            }

            addToBackStack(screen.screenKey)
            localStackCopy.add(screen.screenKey)

            commit()
        }
    }

    private fun isLastNotRootScreen(): Boolean {
        val rootScreenKeyCopy = rootScreen ?: return false
        return localStackCopy.isNotEmpty() && localStackCopy.last() != rootScreenKeyCopy.screenKey
    }
}