package ru.geckolab.mviexample.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.geckolab.mviexample.data.source.PaperGenerator
import ru.geckolab.mviexample.domain.models.Paper
import ru.geckolab.mviexample.domain.repository.PaperRepository
import javax.inject.Inject

class PaperRepositoryImpl @Inject constructor() : PaperRepository {
    override fun loadPapers(): Flow<List<Paper>> = flow {
        delay(300)
        emit(PaperGenerator.generate())
    }
}