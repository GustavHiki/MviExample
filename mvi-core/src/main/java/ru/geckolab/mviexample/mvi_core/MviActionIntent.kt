package ru.geckolab.mviexample.mvi_core

import androidx.viewbinding.ViewBinding

abstract class MviActionIntent<VB : ViewBinding, Event: MviEvent>:
    MviActionView<VB, Event>(), MviExecutor<Event>