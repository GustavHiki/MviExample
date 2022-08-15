package ru.geckolab.mviexample.navigation.root

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geckolab.mviexample.commons_android.models.FragmentAnim
import java.io.Serializable

abstract class AnimFragmentScreen : FragmentScreen, Serializable {
    open var fragmentAnim: FragmentAnim = FragmentAnim.NONE

    companion object {
        operator fun invoke(
            key: String? = null,
            clearContainer: Boolean = true,
            fragmentAnim: FragmentAnim = FragmentAnim.NONE,
            fragmentCreator: Creator<FragmentFactory, Fragment>
        ) = object : AnimFragmentScreen() {
            override val screenKey = key ?: fragmentCreator::class.java.name
            override val clearContainer = clearContainer
            override var fragmentAnim = fragmentAnim
            override fun createFragment(factory: FragmentFactory) = fragmentCreator.create(factory)
        }
    }
}