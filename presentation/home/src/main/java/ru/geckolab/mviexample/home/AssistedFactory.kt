package ru.geckolab.mviexample.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.geckolab.mviexample.commons_android.navigation.RootPage

object AssistedFactory {
    fun homeFactory(
        assistedFactory: HomeFactory,
        rootPage: RootPage
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(rootPage) as T
        }
    }

    @dagger.assisted.AssistedFactory
    interface HomeFactory {
        fun create(rootPage: RootPage): HomeViewModel
    }
}