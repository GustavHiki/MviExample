package ru.geckolab.mviexample.mvi_core

import androidx.viewbinding.ViewBinding

abstract class MviActionView<VB : ViewBinding, Data: MviEvent> {
    open fun render(binding: VB, data: Data) {}
}