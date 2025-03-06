package com.example.polizasliver.data.database

import androidx.room.Database
import com.example.polizasliver.data.database.dao.InfoInsuranceDao
import com.example.polizasliver.data.database.entities.InfoInsuranceEntity

@Database(entities = [InfoInsuranceEntity::class], version = 1)
abstract class InsuranceDatabase {

    abstract fun getInfoInsuranceDao(): InfoInsuranceDao

}