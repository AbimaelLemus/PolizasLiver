package com.example.polizasliver.domain

import com.example.polizasliver.data.InsuranceRepository
import com.example.polizasliver.domain.model.InfoInsuranceItem
import javax.inject.Inject

class DeleteInsuranceUseCase @Inject constructor(
    private val repository: InsuranceRepository
) {

    suspend operator fun invoke(noInsurance: String): Int =
        repository.deleteInsurance(noInsurance)

}