package com.example.polizasliver.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.polizasliver.domain.model.InfoInsuranceItem

@Entity(tableName = "info_insurance")
data class InfoInsuranceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,

    @ColumnInfo(name = "no_insurance") val no_insurance: Double,
    @ColumnInfo(name = "name_insurance") val name_insurance: String,

    @ColumnInfo(name = "clients_name") val clients_name: String,
    @ColumnInfo(name = "direction") val direction: String,
    @ColumnInfo(name = "cp") val cp: Int,
    @ColumnInfo(name = "phone") val phone: Double,
    @ColumnInfo(name = "emergency_contact") val emergency_contact: String,
    @ColumnInfo(name = "emergency_phone") val emergency_phone: Double
)

fun InfoInsuranceItem.toDatabase() = InfoInsuranceEntity(
    no_insurance = noInsurance,
    name_insurance = nameInsurance,

    clients_name = clientsName,
    direction = direction,
    cp = cp,
    phone = phone,
    emergency_contact = emergencyContact,
    emergency_phone = emergencyPhone
)