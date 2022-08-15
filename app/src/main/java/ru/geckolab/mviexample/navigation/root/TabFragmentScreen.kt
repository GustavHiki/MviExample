package ru.geckolab.mviexample.navigation.root

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.androidx.Creator
import ru.geckolab.mviexample.commons_android.models.FragmentAnim
import ru.geckolab.mviexample.commons_android.navigation.RootPage

abstract class TabFragmentScreen : AnimFragmentScreen() {
    abstract fun restoreFragment(fragmentManager: FragmentManager): Fragment?
    abstract val rootPage: RootPage

    companion object {
        operator fun <T : Fragment> invoke(
            rootPage: RootPage,
            clearContainer: Boolean = true,
            fragmentAnim: FragmentAnim = FragmentAnim.NONE,
            fragmentReceiver: ((T) -> Unit)? = null,
            fragmentCreator: Creator<FragmentFactory, Fragment>
        ) = object : TabFragmentScreen() {
            override val rootPage = rootPage
            override val screenKey = rootPage.key
            override val clearContainer = clearContainer
            override var fragmentAnim = fragmentAnim

            override fun createFragment(factory: FragmentFactory) = fragmentCreator.create(factory)

            override fun restoreFragment(fragmentManager: FragmentManager): Fragment? {
                val targetFragment = fragmentManager.findFragmentByTag(rootPage.key) ?: return null

                (targetFragment as? T)?.let { typedFragment ->
                    fragmentReceiver?.invoke(typedFragment)
                }

                return targetFragment
            }
        }
    }
}