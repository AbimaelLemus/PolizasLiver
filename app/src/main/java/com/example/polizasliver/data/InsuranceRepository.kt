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

    suspend fun insertInfoClient(infoInsurance: InfoInsuranceEntity) {
        insuranceDao.insertInfoInsurance(infoInsurance)
    }

    suspend fun getDetailInsurance(noInsurance: String): InfoInsuranceItem {
        val response = insuranceDao.getDetailInsurance(noInsurance)
        return response.toDomain()
    }

    suspend fun deleteInsurance(noInsurance: String): Int =
        insuranceDao.deleteInsurance(noInsurance)


}