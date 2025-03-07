package com.example.polizasliver.domain

import com.example.polizasliver.data.InsuranceRepository
import com.example.polizasliver.data.database.entities.toDatabase
import com.example.polizasliver.domain.model.InfoInsuranceItem
import javax.inject.Inject

class InsertInfoInsuranceUseCase @Inject constructor(
    private val repository: InsuranceRepository
) {

    suspend operator fun invoke(info: InfoInsuranceItem): String {
        val result = repository.insertInfoClient(info.toDatabase())
        return result.toString()
    }

}