package ru.geckolab.mviexample.di.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.geckolab.mviexample.navigation.root.RootTabsFragmentRouter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RootTabsNavigationModule {
    @Provides
    @Singleton
    fun provideTabsCicerone(): Cicerone<RootTabsFragmentRouter> = Cicerone.create(RootTabsFragmentRouter())

    @Provides
    @Singleton
    fun provideTabsRouter(cicerone: Cicerone<RootTabsFragmentRouter>): RootTabsFragmentRouter = cicerone.router

    @Provides
    @Singleton
    fun provideTabsNavigatorHolder(
        cicerone: Cicerone<RootTabsFragmentRouter>
    ): NavigatorHolder = cicerone.getNavigatorHolder()
}