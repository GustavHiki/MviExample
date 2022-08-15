package ru.geckolab.mviexample.di.navigation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.geckolab.mviexample.navigation.child.ChildScreenRouters
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChildNavigationModule {
    @Provides
    @Singleton
    fun provideChildScreenRouters(): ChildScreenRouters = ChildScreenRouters()
}