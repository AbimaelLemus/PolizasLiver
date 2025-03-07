package com.example.polizasliver.domain.model

import com.example.polizasliver.data.database.entities.InfoInsuranceEntity

data class InfoInsuranceItem(
    var noInsurance: String,
    var nameInsurance: String,

    var clientsName: String,
    var direction: String,
    var cp: String,
    var phone: String,
    var emergencyContact: String,
    var emergencyPhone: String
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