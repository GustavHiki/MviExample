package ru.geckolab.mviexample.domain.usecases

import ru.geckolab.mviexample.domain.repository.PaperRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val paperRepository: PaperRepository
) {
    fun loadPapers() = paperRepository.loadPapers()
}