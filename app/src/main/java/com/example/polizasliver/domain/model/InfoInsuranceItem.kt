package com.example.polizasliver.domain.model

import com.example.polizasliver.data.database.entities.InfoInsuranceEntity

data class InfoInsuranceItem(
    val noInsurance: Double,
    val nameInsurance: String,

    val clientsName: String,
    val direction: String,
    val cp: Int,
    val phone: Double,
    val emergencyContact: String,
    val emergencyPhone: Double
)

fun InfoInsuranceEntity.toDomain() = InfoInsuranceItem(
    no_insurance,
    name_insurance,

    clients_name,
    direction,
    cp,
    phone,
    emergency_contact,
    emergency_phone
)