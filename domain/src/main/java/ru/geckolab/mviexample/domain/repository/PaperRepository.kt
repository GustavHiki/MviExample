package ru.geckolab.mviexample.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.geckolab.mviexample.domain.models.Paper

interface PaperRepository {
    fun loadPapers(): Flow<List<Paper>>
}