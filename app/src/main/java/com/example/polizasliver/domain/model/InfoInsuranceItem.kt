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
    var emergencyPhone: String,
    var dateStart: String,
    var dateEnd: String
)

fun InfoInsuranceEntity.toDomain() = InfoInsuranceItem(
    noInsurance = no_insurance,
    nameInsurance = name_insurance,

    clientsName = clients_name,
    direction = direction,
    cp = cp,
    phone = phone,
    emergencyContact = emergency_contact,
    emergencyPhone = emergency_phone,
    dateStart = date_start,
    dateEnd = date_end
)