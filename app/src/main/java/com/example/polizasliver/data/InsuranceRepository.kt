package com.example.polizasliver.data

import com.example.polizasliver.data.database.dao.InfoInsuranceDao
import com.example.polizasliver.data.database.entities.InfoInsuranceEntity
import com.example.polizasliver.domain.model.InfoInsuranceItem
import com.example.polizasliver.domain.model.toDomain
import javax.inject.Inject

class InsuranceRepository @Inject constructor(
    private val insuranceDao: InfoInsuranceDao
) {
    suspend fun getAllInfoInsurance(): List<InfoInsuranceItem> {
        val response = insuranceDao.getAllInfoInsurance()
        return response.map { it.toDomain() }
    }

    suspend fun insertInfoClient(infoInsurance: List<InfoInsuranceEntity>) {
        insuranceDao.insertInfoInsurance(infoInsurance)
    }

    suspend fun clearInfoInsurance(noInsurance: Double) {
        insuranceDao.deleteInsurance(noInsurance)
    }

}