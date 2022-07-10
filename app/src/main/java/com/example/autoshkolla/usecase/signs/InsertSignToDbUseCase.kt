package com.example.autoshkolla.usecase.signs

import com.example.autoshkolla.model.SignModel
import com.example.autoshkolla.repository.main.SignRepository
import javax.inject.Inject

class InsertSignToDbUseCase @Inject constructor(private val signRepository: SignRepository) {
    suspend fun execute(signModel: SignModel): Unit =
        signRepository.insertSignDataToDB(signModel)
}