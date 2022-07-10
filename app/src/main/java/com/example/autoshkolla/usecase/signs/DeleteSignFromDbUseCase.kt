package com.example.autoshkolla.usecase.signs

import com.example.autoshkolla.repository.main.SignRepository
import javax.inject.Inject

class DeleteSignFromDbUseCase @Inject constructor(private val signRepository: SignRepository) {
    suspend fun execute(id: Int) : Unit = signRepository.deleteSignDataFromDB(id)
}