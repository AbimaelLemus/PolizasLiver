package com.example.polizasliver.domain

import com.example.polizasliver.data.InsuranceRepository
import com.example.polizasliver.domain.model.InfoInsuranceItem
import javax.inject.Inject

class GetAllInfoInsuranceUseCase @Inject constructor(
    private val repository: InsuranceRepository
) {

    //devuelve todos las polizas
    suspend operator fun invoke(): List<InfoInsuranceItem>? {
        val result = repository.getAllInfoInsurance()
        if (result.isNotEmpty()) {
            return result
        }
        return null
    }
}