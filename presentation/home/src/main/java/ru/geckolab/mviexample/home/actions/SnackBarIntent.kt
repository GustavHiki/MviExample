package ru.geckolab.mviexample.home.actions

import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import ru.geckolab.mviexample.home.HomeEvent
import ru.geckolab.mviexample.home.databinding.FragmentHomeBinding
import ru.geckolab.mviexample.mvi_core.MviActionIntent
import ru.geckolab.mviexample.mvi_core.Store
import javax.inject.Inject

class SnackBarIntent @Inject constructor() : MviActionIntent<FragmentHomeBinding, HomeEvent.SnackBarClick>() {

    override fun render(binding: FragmentHomeBinding, data: HomeEvent.SnackBarClick) {
        Toast.makeText(binding.root.context, data.text, Toast.LENGTH_SHORT).show()
    }
}