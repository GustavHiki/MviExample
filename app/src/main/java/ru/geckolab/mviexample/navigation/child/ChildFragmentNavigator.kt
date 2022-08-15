package ru.geckolab.mviexample.navigation.child

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geckolab.mviexample.commons_android.models.FragmentAnim
import ru.geckolab.mviexample.commons_android.utils.ext.hideKeyboard
import ru.geckolab.mviexample.navigation.root.AnimFragmentScreen
import ru.geckolab.mviexample.navigation.root.RootTabsFragmentRouter

open class ChildFragmentNavigator(
    private val rootTabRouter: RootTabsFragmentRouter,
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory
) : AppNavigator(activity, containerId, fragmentManager, fragmentFactory) {

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

    override fun activityBack() {
        rootTabRouter.exitTab()
    }
}