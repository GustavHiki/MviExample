package ru.geckolab.mviexample.di.app

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.geckolab.mviexample.commons_android.navigation.ScreensRouter
import ru.geckolab.mviexample.data.repository.PaperRepositoryImpl
import ru.geckolab.mviexample.domain.repository.PaperRepository
import ru.geckolab.mviexample.navigation.ScreensRouterImpl

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun bindScreensRouter(arg: ScreensRouterImpl): ScreensRouter
        @Binds
        abstract fun bindPaperRepository(arg: PaperRepositoryImpl): PaperRepository
    }
}