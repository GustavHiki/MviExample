package ru.geckolab.mviexample.home

import androidx.core.view.isVisible
import ru.geckolab.mviexample.home.databinding.FragmentHomeBinding
import ru.geckolab.mviexample.mvi_core.MviStateView

data class HomeStateView(
    val isLoading: Boolean,
    val text: String
): MviStateView<FragmentHomeBinding>() {

    override fun render(binding: FragmentHomeBinding) = with(binding) {
        progressBar.isVisible = isLoading
        tvTitle.text = text
    }
}