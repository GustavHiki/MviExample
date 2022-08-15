package ru.geckolab.mviexample.navigation.child

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import ru.geckolab.mviexample.commons_android.models.FragmentAnim
import ru.geckolab.mviexample.navigation.root.AnimFragmentScreen

class ChildFragmentRouter : Router() {

    fun navigate(screen: Screen, fragmentAnim: FragmentAnim = FragmentAnim.default) {
        (screen as? AnimFragmentScreen)?.fragmentAnim = fragmentAnim
        super.navigateTo(screen)
    }

    fun backToRoot() {
        backTo(null)
    }
}