package com.example.autoshkolla.usecase.signs

import com.example.autoshkolla.model.SignModel
import com.example.autoshkolla.repository.main.SignRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSignsFromDbUseCase @Inject constructor(private val signRepository: SignRepository) {

    fun execute() : Flow<List<SignModel>> = signRepository.getSignsFromDB()

}