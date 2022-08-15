package ru.geckolab.mviexample.commons_android.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

abstract class BaseViewModel : ViewModel() {
    protected fun <T> Flow<T>.inViewModel(): Job = launchIn(viewModelScope)
}