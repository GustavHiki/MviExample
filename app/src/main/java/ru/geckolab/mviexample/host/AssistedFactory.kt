package ru.geckolab.mviexample.host

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.geckolab.mviexample.commons_android.navigation.RootPage

object AssistedFactory {
    fun homeHostFactory(
        assistedFactory: HomeHostFactory,
        rootPage: RootPage
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return assistedFactory.create(rootPage) as T
        }
    }

    @dagger.assisted.AssistedFactory
    interface HomeHostFactory {
        fun create(rootPage: RootPage): HostViewModel
    }
}