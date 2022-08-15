package ru.geckolab.mviexample.navigation

import ru.geckolab.mviexample.commons_android.navigation.RootPage
import ru.geckolab.mviexample.home.HomeFragment
import ru.geckolab.mviexample.host.HostFragment
import ru.geckolab.mviexample.navigation.root.AnimFragmentScreen
import ru.geckolab.mviexample.navigation.root.TabFragmentScreen

object Screens {
    fun tabHost(rootPage: RootPage) = TabFragmentScreen<HostFragment>(rootPage) { HostFragment.newInstance(rootPage) }

    fun home(rootPage: RootPage) = AnimFragmentScreen("screen:home") { HomeFragment.newInstance(rootPage) }
}