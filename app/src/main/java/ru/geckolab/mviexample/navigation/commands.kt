package ru.geckolab.mviexample.navigation

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Screen

data class ForwardTab(val screen: Screen) : Command

data class NewTabRootScreen(val screen: Screen) : Command
