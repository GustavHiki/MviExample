package ru.geckolab.mviexample.mvi_core

import androidx.viewbinding.ViewBinding

abstract class MviStateView<VB : ViewBinding> {
    abstract fun render(binding: VB)
}