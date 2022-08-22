package ru.geckolab.mviexample.domain.models

import java.io.Serializable

data class Paper(
    val title: String,
    val content: String,
    val imageUrl: String,
): Serializable